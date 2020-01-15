let vm = new Vue({
    el: "#main-container",
    data: {
        pageInfo: '',
        params: {
            type:""
        }
    },
    methods: {
        selectPage: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: 'manager/syslog/selectPage',
                method: 'post',
                data: this.params
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(error => {
                layer.msg(error);
            })
        },
        detailLog: function (id) {
            axios({
                url: "manager/syslog/toDetail",
                params: {id: id}
            }).then(response => {
                layer.obj = response.data;
                layer.open({
                    type: 2,
                    area: ['80%', '80%'],
                    content: 'manager/syslog/toDetailPage',
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