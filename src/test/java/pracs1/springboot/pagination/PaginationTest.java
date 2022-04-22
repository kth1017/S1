package pracs1.springboot.pagination;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaginationTest {

    @Test
    public void PaginationConstTest(){
        //given
        int pageSize = 10;
        int blockSize = 10;

        //when
        int totalListCnt = 111;
        int page = 1;
        Pagination pagination = new Pagination(totalListCnt, page);

        //then
        assertThat(pagination.getTotalPageCnt()).isEqualTo((int) Math.ceil(totalListCnt * 1.0 / pageSize));
        assertThat(pagination.getBlock()).isEqualTo((int) Math.ceil((page * 1.0) / blockSize));
        assertThat(pagination.getStartIndex()).isEqualTo((page - 1) * pageSize);
    }

    @Test
    public void indexPaginationCreateTest(){
        //given
        //when
        Pagination indexPagination = Pagination.indexPaginationCreate(5);

        //then
        assertThat(indexPagination.getStartIndex()).isEqualTo(0);
        assertThat(indexPagination.getPageSize()).isEqualTo(5);
    }

    @Test
    public void validationTest(){
        //given
        Pagination pagination = new Pagination(10, 1);

        //when
        pagination.setEndPage(2);
        pagination.setPrevBlock(0);
        pagination.setNextBlock(2);
        pagination.validationRun();

        //then
        assertThat(pagination.getEndPage()).isEqualTo(1);
        assertThat(pagination.getPrevBlock()).isEqualTo(1);
        assertThat(pagination.getNextBlock()).isEqualTo(1);
    }
}