package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.comparators.ads.AdAscComparatorById;
import com.uniloftsky.spingframework.spring5advertismentservice.comparators.ads.AdDescComparatorById;
import com.uniloftsky.spingframework.spring5advertismentservice.exceptions.NotFoundException;
import com.uniloftsky.spingframework.spring5advertismentservice.filter.AdvertisementCriteriaRepository;
import com.uniloftsky.spingframework.spring5advertismentservice.filter.AdvertisementPage;
import com.uniloftsky.spingframework.spring5advertismentservice.filter.AdvertisementSearchCriteria;
import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;
import com.uniloftsky.spingframework.spring5advertismentservice.model.Status;
import com.uniloftsky.spingframework.spring5advertismentservice.model.User;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.AdvertisementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementCriteriaRepository advertisementCriteriaRepository;
    private final UserService userService;
    private static final Comparator<Advertisement> ascComparatorById = new AdAscComparatorById();
    private static final Comparator<Advertisement> descComparatorById = new AdDescComparatorById();

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, AdvertisementCriteriaRepository advertisementCriteriaRepository, UserService userService) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementCriteriaRepository = advertisementCriteriaRepository;
        this.userService = userService;
    }

    @Override
    public Set<Advertisement> findAll() {
        Set<Advertisement> advertisements = new HashSet<>();
        advertisementRepository.findAll().iterator().forEachRemaining(advertisements::add);
        return advertisements;
    }

    @Override
    public Set<Advertisement> findAllActive() {
        Set<Advertisement> ads = new HashSet<>();
        advertisementRepository.findAll().iterator().forEachRemaining(ads::add);
        return ads.stream().filter(ad -> ad.getStatus().equals(Status.ACTIVE_AD)).collect(Collectors.toSet());
    }

    @Override
    public Set<Advertisement> findAllChecking() {
        Set<Advertisement> ads = new TreeSet<>(descComparatorById);
        advertisementRepository.findAll().iterator().forEachRemaining(ads::add);
        return ads.stream().filter(ad -> ad.getStatus().equals(Status.CHECK_AD)).collect(Collectors.toSet());
    }

    @Override
    public Page<Advertisement> findAllCheckingPage(Pageable pageable) {
        return advertisementRepository.findAllByStatus(pageable, Status.CHECK_AD);
    }

    @Override
    public TreeSet<Advertisement> getLastAds(Comparator<Advertisement> comparator, int count, Status status) {
        return findAllSortedBy(comparator).stream().filter(ad -> ad.getStatus().equals(status)).limit(count).collect(toCollection(() -> new TreeSet<>(comparator)));
    }

    @Override
    public TreeSet<Advertisement> findAllSortedBy(Comparator<Advertisement> comparator) {
        TreeSet<Advertisement> ads = new TreeSet<>(comparator);
        advertisementRepository.findAll().iterator().forEachRemaining(ads::add);
        return ads;
    }

    @Override
    public Set<Advertisement> findAllSortedByByUser(Comparator<Advertisement> comparator, User user) {
        return findAllSortedBy(comparator).stream().filter(ad -> ad.getUser() == user).collect(toCollection(() -> new TreeSet<>(comparator)));
    }

    @Override
    public Page<Advertisement> getFilteredAds(AdvertisementPage advertisementPage, AdvertisementSearchCriteria advertisementSearchCriteria) {
        return advertisementCriteriaRepository.findAllWithFilters(advertisementPage, advertisementSearchCriteria);
    }

    @Override
    public Set<Advertisement> getStatusAdsByUser(Status status, User user, Comparator<Advertisement> comparator) {
        Set<Advertisement> ads = new TreeSet<>(comparator);
        advertisementRepository.findAll().iterator().forEachRemaining(ads::add);
        return ads.stream().filter(ad -> ad.getStatus().equals(status) && ad.getUser() == user).collect(toCollection(() -> new TreeSet<>(comparator)));
    }

    @Override
    public Advertisement submit(Advertisement advertisement) {
        advertisement.setStatus(Status.ACTIVE_AD);
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement block(Advertisement advertisement) {
        advertisement.setStatus(Status.BLOCK_AD);
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement findById(Long aLong) {
        Optional<Advertisement> advertisementOptional = advertisementRepository.findById(aLong);
        if (advertisementOptional.isEmpty()) {
            throw new NotFoundException("???????????????? ?? ???????????? ID ???? ????????????????");
        }
        return advertisementOptional.get();
    }

    @Override
    public Advertisement save(Advertisement obj) {
        return advertisementRepository.save(obj);
    }

    @Override
    public Advertisement save(Advertisement obj, Authentication authentication) {
        //shit code, have to refactor
        if (obj.getResponsibilities().isEmpty()) {
            obj.setResponsibilities(null);
        }
        if (obj.getJobNatural().isEmpty()) {
            obj.setJobNatural(null);
        }
        if (obj.getOffer().isEmpty()) {
            obj.setOffer(null);
        }
        if (obj.getQualifications().isEmpty()) {
            obj.setQualifications(null);
        }
        obj.setPublicDate(LocalDate.now());
        obj.setUser(userService.findByUsername(authentication.getName()));
        return advertisementRepository.save(obj);
    }

    @Override
    public void delete(Advertisement obj) {
        advertisementRepository.delete(obj);
    }
}
