var main = {
    init : function () {
        var _this = this;
        $('#btn-search').on('click', function () {
            _this.search();
        });
    },
    search : function () {
        var data = {
            type: $('#type').val(),
            keyword: $('#keyword').val(),
        };
        $.ajax({
            type: 'GET',
            url: '/api/search',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('검색 완료');
            window.location.href = '/posts-list/search';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};

main.init();