package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.service.RecruitmentInfoService;
import com.tcutma.realstate.domain.RecruitmentInfo;
import com.tcutma.realstate.repository.RecruitmentInfoRepository;
import com.tcutma.realstate.service.dto.RecruitmentInfoDTO;
import com.tcutma.realstate.service.mapper.RecruitmentInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing RecruitmentInfo.
 */
@Service
@Transactional
public class RecruitmentInfoServiceImpl implements RecruitmentInfoService {

    private final Logger log = LoggerFactory.getLogger(RecruitmentInfoServiceImpl.class);

    private final RecruitmentInfoRepository recruitmentInfoRepository;

    private final RecruitmentInfoMapper recruitmentInfoMapper;

    public RecruitmentInfoServiceImpl(RecruitmentInfoRepository recruitmentInfoRepository, RecruitmentInfoMapper recruitmentInfoMapper) {
        this.recruitmentInfoRepository = recruitmentInfoRepository;
        this.recruitmentInfoMapper = recruitmentInfoMapper;
    }

    /**
     * Save a recruitmentInfo.
     *
     * @param recruitmentInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RecruitmentInfoDTO save(RecruitmentInfoDTO recruitmentInfoDTO) {
        log.debug("Request to save RecruitmentInfo : {}", recruitmentInfoDTO);
        RecruitmentInfo recruitmentInfo = recruitmentInfoMapper.toEntity(recruitmentInfoDTO);
        recruitmentInfo = recruitmentInfoRepository.save(recruitmentInfo);
        return recruitmentInfoMapper.toDto(recruitmentInfo);
    }

    /**
     * Get all the recruitmentInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RecruitmentInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RecruitmentInfos");
        return recruitmentInfoRepository.findAll(pageable)
            .map(recruitmentInfoMapper::toDto);
    }


    /**
     * Get one recruitmentInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RecruitmentInfoDTO> findOne(Long id) {
        log.debug("Request to get RecruitmentInfo : {}", id);
        return recruitmentInfoRepository.findById(id)
            .map(recruitmentInfoMapper::toDto);
    }

    /**
     * Delete the recruitmentInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RecruitmentInfo : {}", id);
        recruitmentInfoRepository.deleteById(id);
    }
}
