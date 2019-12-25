let vm = new Vue({
    el: "#main-container",
    data: {
        pageInfo: '',
        params: {
            type: '',
            check: ''
        }
    },
    methods: {
        selectPage: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: 'manager/qualification/toList',
                method: 'post',
                data: this.params
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(error => {
                layer.msg(error);
            })
        },
        toUpdate: function (id) {
            axios({
                url: 'manager/qualification/toUpdate',
                params: {id:id}
            }).then(response => {
                layer.obj = response.data.qualification;
                layer.oid = response.data.oid;
                layer.open({
                    type: 2,
                    area: ['80%', '80%'],
                    content: 'manager/qualification/toUpdatePage',
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