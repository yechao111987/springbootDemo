var vm = new Vue({
    el: '#app',
    data: {
        username: '叶超',
        dpi: 400,
        file: '',
        selected: 1,
        container: 1,
        tasks: [],
        popupVisible: false,
        files: [],
        taskId: '',
        loading: false,
    },
    created() {
        this.selected = 1;
    },
    mounted() {
        var _this = this;
        var url = "/api/pdf/task/list";
        axios.get(url).then(function (res) {
            if (res.data.code == '200') {
                _this.tasks = res.data.dataResult;
            } else {
                _this.$toast(res.data.message);
            }
        }, function () {
            _this.$toast("http fail");
        })

    },
    methods: {
        uploadFile(event) {
            var forms = new FormData();
            forms.append('file', event.target.files[0]);
            console.info(forms);
            let configs = {
                headers: {'Content-Type': 'multipart/form-data; charset=utf-8'}
            };
            var url = "/api/file/upload";
            var _this = this;
            axios.post(url, forms, configs).then(function (res) {
                console.info(res);
                if (res.data.code == '200') {
                    _this.$toast({
                        message: '文件上传成功',
                        iconClass: 'icon icon-success'
                    });
                    _this.file = res.data.dataResult.name;
                } else {
                    _this.$toast(res.data.message);
                }
            }, function (res) {
                _this.$toast("文件上传失败");
            })
        },
        toImage() {
            console.info("to image");
            var url = "/api/pdf/task/add";
            var _this = this;
            axios.get(url, {params: {file: _this.file, dpi: _this.dpi}}).then(function (res) {
                if (res.data.code == '200') {
                    _this.$toast("提交成功");
                    _this.tasks.push(res.data.dataResult);
                } else {
                    _this.$toast(res.data.message);
                }
            }, function (res) {
                _this.$toast("提交失败");
            })
        },
        view(p) {
            this.popupVisible = true;
            var _this = this;
            _this.taskId = p.taskId;
            var url = "/api/pdf/task/" + p.taskId;
            axios.get(url).then(function (res) {
                if (res.data.code == '200') {
                    _this.files = res.data.dataResult.dest;
                } else {
                    _this.$toast(res.data.message);
                }
            }, function () {
                _this.$toast("请求失败");
            })
        },
        deleteTask(p) {
            var _this = this;
            _this.taskId = p.taskId;
            var url = "/api/pdf/task/delete/" + p.taskId;
            axios.get(url).then(function (res) {
                if (res.data.code == '200') {
                    _this.$toast("删除成功");
                    console.info(p.taskId);
                    _this.tasks.forEach(function (item, index) {
                        if (item.taskId == p.taskId) {
                            console.info(item);
                            _this.tasks.splice(item, 1);
                        }
                    });
                    console.info(_this.tasks);
                } else {
                    _this.$toast(res.data.message);
                }
            }, function () {
                _this.$toast("请求失败");
            })
        },

        download(p) {
            var _this = this;
            var taskUrl = "/api/pdf/task/" + p.taskId;
            var url = "/api/pdf/task/dowload/" + p.taskId + "?fileName=";
            axios.get(taskUrl).then(function (res) {
                if (res.data.code == '200') {
                    var names = res.data.dataResult.dest;
                    console.info(names);
                    let triggerDelay = 100;
                    let removeDelay = 1000;
                    for (var i = 0; i < names.length; i++) {
                        // window.location.href = url + "?fileName=" + names[i];
                        var temp = url + names[i];
                        _this._createIFrame(temp, i * triggerDelay, removeDelay);
                    }
                    // names.forEach(function (x, index, a) {
                    //     window.location.href = url + "?fileName=" + x;
                    // });
                } else {
                    _this.$toast(res.data.message);
                }
            }, function () {
                _this.$toast("请求失败");
            });
        },
        downloadOne(item) {
            var _this = this;
            var url = "/api/pdf/task/dowload/" + _this.taskId + "?fileName=" + item;
            window.location.href = url;
        },
        _createIFrame(url, triggerDelay, removeDelay) {
            //动态添加iframe，设置src，然后删除
            setTimeout(function () {
                const iframe = document.createElement("iframe");
                iframe.style.display = "none"; // 防止影响页面
                iframe.style.height = 0; // 防止影响页面
                iframe.src = url;
                document.body.appendChild(iframe);
                setTimeout(function () {
                    iframe.remove();
                }, removeDelay);
            }, triggerDelay);
        },
        loadMore() {
            this.loading = true;
            setTimeout(() => {
                // let last = this.list[this.files.length - 1];
                // for (let i = 1; i <= 10; i++) {
                //     this.list.push(last + i);
                // }
                this.loading = false;
            }, 2500);
        },
    }
})
