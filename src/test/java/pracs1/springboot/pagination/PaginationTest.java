package pracs1.springboot.pagination;

import org.junit.jupiter.api.Test;
import pracs1.springboot.posts.pagination.Pagination;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void validationCommonTest(){
        //given
        Pagination pagination = new Pagination(10, 1);

        //when
        pagination.setEndPage(2);
        pagination.setPrevBlockPage(0);
        pagination.setNextBlockPage(2);
        pagination.validationRun();

        //then
        assertThat(pagination.getEndPage()).isEqualTo(1);
        assertThat(pagination.getPrevBlockPage()).isEqualTo(1);
        assertThat(pagination.getNextBlockPage()).isEqualTo(1);
    }

    @Test
    public void validationZeroTest(){
        //given
        Pagination pagination = new Pagination(0, 1);

        //when
        pagination.validationRun();

        //then
        assertThat(pagination.getTotalPageCnt()).isEqualTo(1);
        assertThat(pagination.getEndPage()).isEqualTo(1);
        assertThat(pagination.getPrevBlockPage()).isEqualTo(1);
        assertThat(pagination.getNextBlockPage()).isEqualTo(1);

    }
}