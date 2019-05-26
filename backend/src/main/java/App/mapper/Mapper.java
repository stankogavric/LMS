package App.mapper;

import java.util.Collection;

public interface Mapper<E, EDTO> {
	
	EDTO toDTO(E e);
	E toEntity(EDTO edto);
	Collection<EDTO> toDTO(Collection<E> es);
	Collection<E> toEntityList(Collection<EDTO> edtos);
}
