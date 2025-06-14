package com.example.demo.generics;

import java.util.List;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public abstract class BaseServiceImpl <E extends BaseEntity, ReqDTO extends BaseDTO, ResDTO extends BaseDTO>
        implements BaseService<E, ReqDTO, ResDTO>{
	protected final BaseRepository<E> baseRepository;
    protected final BaseMapper<E, ReqDTO, ResDTO> baseMapper;	
	
	@Override
    public List<ResDTO> findAll() {
        return baseMapper.toDtoList(baseRepository.findAll());
    }

    @Override
    public ResDTO findById(Long id) {
        return baseRepository.findById(id)
            .map(baseMapper::toDTO)
            .orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    @Override
    public ResDTO save(ReqDTO dto) {
        E entity = baseMapper.toEntity(dto);
        return baseMapper.toDTO(baseRepository.save(entity));
    }

    @Override
    public ResDTO update(Long id, ReqDTO dto) {
        E entity = baseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
        baseMapper.updateEntityFromDto(dto, entity);
        return baseMapper.toDTO(baseRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        baseRepository.deleteById(id);
    }
}