let vm = new Vue({
    el: "#main-container",
    data: {
        pageInfo: '',
        demand:{

        }
    },
    methods: {
        //分页查询
        selectPage: function (pageNum, PageSize) {
            axios({
                url: "manager/demand/toList",
                params: {pageNum: pageNum, PageSize: PageSize}
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(function (error) {
                layer.msg(error);
            })
        },
        toUpdate: function (id) {
            axios({
                url: "manager/demand/toUpdate",
                params: {id: id}
            }).then(response => {
                layer.obj = response.data;
                layer.open({
                    type:2,
                    area:['80%','80%'],
                    content:'manager/demand/toUpdatePage',
                    end:()=>{
                        this.selectPage(1,5)
                    }
                })
            }).catch(error=>{
                layer.msg(error);
            })
        },
        detailDemand: function (id) {
            axios({
                url: "manager/demand/toDetail",
                params: {id: id}
            }).then(response => {
                layer.obj = response.data;
                layer.open({
                    type: 2,
                    area: ['80%', '80%'],
                    content: 'manager/demand/toDetailPage',
                    end: () => {
                        this.selectPage(1, 5)
                    }
                })
            }).catch(error => {
                layer.msg(error);
            })
        }
    },
    created: function () {
        this.selectPage(1, 5);
    }
});