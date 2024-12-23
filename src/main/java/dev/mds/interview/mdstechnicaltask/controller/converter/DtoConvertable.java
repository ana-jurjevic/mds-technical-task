package dev.mds.interview.mdstechnicaltask.controller.converter;

public interface DtoConvertable<E, D> {
	D convertToDto(E entity);
}
