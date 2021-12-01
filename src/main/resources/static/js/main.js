(function ($) {
    getWishList();
    var search_result = new Vue({
        el: '#search-result',
        data: {
            search_result : {}
        },
        methods: {

            addWish: function (item) {
                console.log("add");
                $.ajax({
                    type: "POST" ,
                    async: true ,
                    url: "/api/food",
                    timeout: 3000,
                    data: JSON.stringify(item),
                    contentType: "application/json",
                    error: function (request, status, error) {

                    },
                    success: function (response, status, request) {
                        if(window.confirm("위시리스트에 추가 되었습니다. 위시리스트로 이동하시겠습니까?")){
                            window.location.assign("http://localhost:8080/pages/wishlist");
                            getWishList();
                        } else {

                        }
                    }
                });
            }
        }
    });

    var wish_list = new Vue({
        el: '#wish-list',
        data: {
            wish_list : {}
        },
        methods: {
            addVisit: function (id) {
                $.ajax({
                    type: "PUT" ,
                    async: true ,
                    url: `/api/food/${id}`,
                    timeout: 3000
                }).always(function() {
                    getWishList();
                    });
            },
            deleteWish: function (id) {
                $.ajax({
                    type: "DELETE" ,
                    async: true ,
                    url: `/api/food/${id}`,
                    timeout: 3000
                }).always(function() {
                     getWishList();
                     console.log(id);
                     });
            }
        }
    });

    $("#searchButton").click(function () {
        const query = $("#searchBox").val();
        $.get(`/api/food/search?query=${query}`, function (response) {
            search_result.search_result = response;
            $('#search-result').attr('style','visible');
        });
    });

    $("#searchBox").keydown(function(key) {
        if (key.keyCode === 13) {
            const query = $("#searchBox").val();
            $.get(`/api/food/search?query=${query}`, function (response) {
                search_result.search_result = response;
                $('#search-result').attr('style','visible');
            });
        }
    });

    $("#listButton").click(function (){
        window.location.assign("http://localhost:8080/pages/wishlist");
        getWishList();
    });

    function getWishList(){
        $.get(`/api/food/all`, function (response) {
            wish_list.wish_list = response;
        });
    }

    $(document).ready(function () {
        console.log("init")
    });

})(jQuery);
