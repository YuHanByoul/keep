package com.kbrainc.plum.rte.util.excel;

@FunctionalInterface
public interface RowMapper<T> {
    boolean mapData(T data, CellMapper mapper, int idx) throws Exception;
}
