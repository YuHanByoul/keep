var apiManager = {
    init: function() {
        // 실시간 대기정보
        //this.getCtprvnRltmMesureDnsty("인천");
        // 오늘/내일/모레 예보
        //this.getMinuDustFrcstDspth("PM25");
        // 우리동네 대기정보
        //this.getMsrstnAcctoRltmMesureDnsty();
        // 수위정보
        //this.getWaterlevelList();
        // 댐정보
        //this.getDamList();
        // 보정보
        //this.getBoList();
        // 농업기상 관측지점 정보
        //this.getObsrSpotList();
        // 농업기상 관측 정보(시간)
        //this.getWeatherTimeList("477802A001");
        // 농업기상 관측 정보(일)
        //this.getWeatherMonDayList("477802A001");
        // 전기자동차 충전소 정보 
        //this.getChargerInfo("11", "11440");

        this.registEvent();    
    }
    
    , registEvent: function() {
        // TODO
    }
    
    // 실시간 대기정보
    , getCtprvnRltmMesureDnsty: function(sidoName, tabIdx, grid) {
        $.ajax({
            url: "/front/api/getCtprvnRltmMesureDnsty.do"
            , type: "GET"
            , data: {sidoName: sidoName} 
            , cache: false
            , success: function(result) {
                if(result.response && result.response.header.resultCode == "00") {
                    main.setWidgetStatus("sucess", result.response.body.items[0].dataTime + "기준");
                    apiManager.setCtprvnRltmMesureDnsty(result.response.body, tabIdx, grid);
                } else {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    , setCtprvnRltmMesureDnsty: function(data, tabIdx, grid) {
        for(var i = 0; i < data.totalCount; i++) {
            var info = data.items[i];
            var sidoName = info.sidoName;
            var stationName = info.stationName;
            var displayInfo = this.getRltmMesureDnstyInfo(tabIdx, info);
            
            $(grid).append(
                '<tr><td>' + sidoName + '</td><td>' + stationName + '</td><td><span class="fc-level' + displayInfo.grade + '">' + displayInfo.value + '</span></td></tr>'
            );
        }
    }
    
    // 대기 노출 정보
    , getRltmMesureDnstyInfo: function(idx, data) {
        var info = {
          value: "-"
          , grade: "-"  
        };
        switch(idx) {
            case 0:
                info.grade = data.khaiGrade;
                info.value = this.getGrade(data.khaiGrade) + "(" + data.khaiValue + ")"; 
            break;
            
            case 1:
                info.grade = data.pm25Grade;
                info.value = data.pm25Value + "㎍/㎥(1시간),<br/>" + data.pm25Value24 + "㎍/㎥(24시간)";
            break;
            
            case 2:
                info.grade = data.pm10Grade;
                info.value = data.pm10Value + "㎍/㎥(1시간),<br/>" + data.pm10Value24 + "㎍/㎥(24시간)";
            break;
            
            case 3:
                info.grade = data.o3Grade; 
                info.value = data.o3Value;
            break;
            
            case 4:
                info.grade = data.no2Grade;
                info.value = data.no2Value;
            break;
            
            case 5:
                info.grade = data.coGrade;
                info.value = data.coValue;
            break;
            
            case 6:
                info.grade = data.so2Grade;
                info.value = data.so2Value;
            break;
            
            default:
        }
        
        return info;
    }
    
    // 대기 상태
    , getGrade: function(grade) {
        return grade == 1 ? "좋음" : grade == 2 ? "보통" : grade == 3 ? "나쁨" : grade == 4 ? "매우나쁨" : "-";
    }
    
    // 오늘/내일/모레 예보
    , getMinuDustFrcstDspth: function(informCode, dateIdx, grid) {
        $.ajax({
            url: "/front/api/getMinuDustFrcstDspth.do"
            , type: "GET"
            , data: {searchDate: $.datepicker.formatDate("yy-mm-dd", new Date()), informCode: informCode} 
            , cache: false
            , success: function(result) {
                if(result.response && result.response.header.resultCode == "00") {
                    apiManager.setMinuDustFrcstDspth(result.response.body, dateIdx, grid);
                } else {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    , setMinuDustFrcstDspth: function(data, dateIdx, grid) {
        var now = new Date();
        var today = $.datepicker.formatDate("yy-mm-dd", now);
        var tomorrow = $.datepicker.formatDate("yy-mm-dd", new Date(now.setDate(now.getDate() + 1)));
        var afterTomorrow = $.datepicker.formatDate("yy-mm-dd", new Date(now.setDate(now.getDate() + 1)));
        var todayInfoList = "예보정보가 없습니다.", tomorrowInfoList = "예보정보가 없습니다.", afterTomorrowInfoList = "예보정보가 없습니다.";
        var dateTime;
        for(var i = 0, len = data.totalCount; i < len; i++) {
            var info = data.items[i];
            if(info.informData == today && todayInfoList == "예보정보가 없습니다.") {
                dateTime = info.dataTime;
                todayInfoList = info.informGrade.split(",");
            } else if(info.informData == tomorrow && tomorrowInfoList == "예보정보가 없습니다.") {
                dateTime = info.dataTime;
                tomorrowInfoList = info.informGrade.split(",");
            } else if(info.informData == afterTomorrow && afterTomorrowInfoList == "예보정보가 없습니다.") {
                dateTime = info.dataTime;
                afterTomorrowInfoList = info.informGrade.split(",");
            }
        }
        if((dateIdx == 0 && todayInfoList == "예보정보가 없습니다.") || (dateIdx == 1 && tomorrowInfoList == "예보정보가 없습니다.") || 
          (dateIdx == 2 && afterTomorrowInfoList == "예보정보가 없습니다.")) {
            main.setWidgetStatus("error", null, "예보정보가 없습니다.");
        } else {
            var data, area, grade, className;
            var list = dateIdx == 0 ? todayInfoList : dateIdx == 1 ? tomorrowInfoList : afterTomorrowInfoList;
            for(var i = 0, len = list.length; i < len; i++) {
                data = list[i];
                area = $.trim(data.split(":")[0]);
                grade = $.trim(data.split(":")[1]);
                className = grade == "좋음" ? "fc-level1" : grade == "보통" ? "fc-level2" : grade == "나쁨" ? "fc-level3" : "fc-level4";
                $(grid).append(
                    '<tr><td>' + area + '</td>' + '<td><span class="' + className + '"><i></i>' + grade + '</span></td></tr>' 
                ); 
            }
            main.setWidgetStatus("sucess", dateTime);
        }
    }
    
    // 우리동네 대기정보
    , getMsrstnAcctoRltmMesureDnsty: function(stationName, grid) {
        $.ajax({
            url: "/front/api/getMsrstnAcctoRltmMesureDnsty.do"
            , type: "GET"
            , data: {stationName: stationName} 
            , cache: false
            , success: function(result) {
                if(result.response && result.response.header.resultCode == "00") {
                    if(result.response.body.items.length == 0) {
                        main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                    } else {
                        main.setWidgetStatus("sucess", result.response.body.items[0].dataTime + "기준");
                        apiManager.setMsrstnAcctoRltmMesureDnsty(result.response.body, grid);
                    }
                } else {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    , setMsrstnAcctoRltmMesureDnsty: function(data, grid) {
        var info = data.items[0];
        $(grid).append(
            '<tr><td class="al pt5 pb5">초미세먼지(PM-2.5)</td><td class="al pt5 pb5"><span class="fc-level' + info.pm25Grade + '"><i></i>' + this.getGrade(info.pm25Grade) + '</span></td>' +
            '<td class="al pt5 pb5">' + info.pm25Value + '㎍/㎥(1h), ' + info.pm25Value24 + '㎍/㎥(24h)</td></tr>' 
        );
        $(grid).append(
            '<tr><td class="al pt5 pb5">미세먼지(PM-10)</td><td class="al pt5 pb5"><span class="fc-level' + info.pm10Grade + '"><i></i>' + this.getGrade(info.pm25Grade) + '</span></td>' +
            '<td class="al pt5 pb5">' + info.pm10Value + '㎍/㎥(1h), ' + info.pm10Value24 + '㎍/㎥(24h)</td></tr>' 
        );
        $(grid).append(
            '<tr><td class="al pt5 pb5">오존(O3)</td><td class="al pt5 pb5"><span class="fc-level' + info.o3Grade + '"><i></i>' + this.getGrade(info.o3Grade) + '</span></td>' +
            '<td class="al pt5 pb5">' + info.o3Value + 'ppm</td></tr>' 
        );
        $(grid).append(
            '<tr><td class="al pt5 pb5">이산화질소(NO2)</td><td class="al pt5 pb5"><span class="fc-level' + info.no2Grade + '"><i></i>' + this.getGrade(info.no2Grade) + '</span></td>' +
            '<td class="al pt5 pb5">' + info.no2Value + 'ppm</td></tr>' 
        );
        $(grid).append(
            '<tr><td class="al pt5 pb5">일산화탄소(CO)</td><td class="al pt5 pb5"><span class="fc-level' + info.coGrade + '"><i></i>' + this.getGrade(info.coGrade) + '</span></td>' +
            '<td class="al pt5 pb5">' + info.coValue + 'ppm</td></tr>' 
        );
        $(grid).append(
            '<tr><td class="al pt5 pb5">아황산가스(SO2)</td><td class="al pt5 pb5"><span class="fc-level' + info.so2Grade + '"><i></i>' + this.getGrade(info.so2Grade) + '</span></td>' +
            '<td class="al pt5 pb5">' + info.so2Value + 'ppm</td></tr>' 
        );
    }
    
    , onPostPopup: function() {
        new daum.Postcode({
            oncomplete: function(data) {
                apiManager.getTMStdrCrdnt(data.bname).then(function(response) {
                    if(response.totalCount == 0) {
                        main.setWidgetStatus("error", null, "검색한 주소와 가까운 측정소가 없습니다.<br/>주소를 다시 검색해 주세요.");
                    } else {
                        var info, item;
                        for(var i = 0; i < response.totalCount; i++) {
                            item = response.items[i];
                            if(item.sggName == data.sigungu) {
                                info = item;
                                break;
                            }
                        }
                        apiManager.getNearbyMsrstnList(data, info);                        
                    }
                }).catch(function(error) {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                });
            }
        }).open();
    }
    
    , getTMStdrCrdnt: function(umdName) {
        return new Promise(function(resolve, reject) {
            $.ajax({
                url: "/front/api/getTMStdrCrdnt.do"
                , type: "GET"
                , data: {umdName: umdName} 
                , cache: false
                , success: function(result) {
                    if(result.response && result.response.header.resultCode == "00") {
                        resolve(result.response.body);
                    } else {
                        reject(new Error("서비스 제공상태가 원할하지 않습니다."));
                    }
                }
            });
        });
    }
    
    , getNearbyMsrstnList: function(postData, tmData) {
        $.ajax({
            url: "/front/api/getNearbyMsrstnList.do"
            , type: "GET"
            , data: {tmX: tmData.tmX, tmY: tmData.tmY} 
            , cache: false
            , success: function(result) {
                if(result.response && result.response.header.resultCode == "00") {
                    var stationInfo = result.response.body.items[0];
                    var confirmMsg = postData.address + "(" + postData.bname + ")" + "과(와)\n가장 가까운 측정소는 『" + stationInfo.stationName + " 측정소』입니다.\n해당 측정소의 대기정보를 조회 하시겠습니까?";
                    if(confirm(confirmMsg)) {
                        localStorage.setItem("nearMsrstn", stationInfo.stationName);
                        main.onSearch();    
                        //apiManager.getMsrstnAcctoRltmMesureDnsty();    
                    }
                } else {
                    main.setWidgetStatus("error", null, "서비스 제공상태가 원할하지 않습니다.<br/>잠시후 다시 시도해주세요.");
                }
            }
        });
    }
    
    // 수위정보
    , getWaterlevelList: function(sidoName) {
        $.ajax({
            url: "/front/api/getWlobsList.do"
            , type: "GET"
            , data: {hydroType: "waterlevel"} 
            , cache: false
            , success: function(result) {
                console.log(result);
                try {
                    apiManager.getWlobsList(result.wlobsList.content, result.obsrvnList.content, sidoName);
                } catch {
                    console.log("서비스 제공상태가 원할하지 않습니다.");
                }
            }
        });
    }
    
    // 댐정보
    , getDamList: function() {
        $.ajax({
            url: "/front/api/getWlobsList.do"
            , type: "GET"
            , data: {hydroType: "dam"} 
            , cache: false
            , success: function(result) {
                console.log(result);
                try {
                } catch {
                    console.log("서비스 제공상태가 원할하지 않습니다.");
                }
            }
        });
    }
    
    // 보정보
    , getBoList: function() {
        $.ajax({
            url: "/front/api/getWlobsList.do"
            , type: "GET"
            , data: {hydroType: "bo"} 
            , cache: false
            , success: function(result) {
                console.log(result);
                try {
                } catch {
                    console.log("서비스 제공상태가 원할하지 않습니다.");
                }
            }
        });
    }
    
    , getWlobsList: function(wlobsList, obsrvnList, sidoName) {
        var list = [];
        var addr;
        for(var i = 0, len = wlobsList.length; i < len; i++) {
            var data = wlobsList[i];
            addr = data.addr;
            if(addr && addr != " ") {
                addr = addr.split(" ")[0];
                if(sidoName == "충북" && addr == "충청북도") {
                    list.push(data);
                } else if(sidoName == "충남" && addr == "충청남도") {
                    list.push(data);
                } else if(sidoName == "경북" && (addr == "경상북도" || addr == "경북" || addr == "겅상북도")) {
                    list.push(data);
                } else if(sidoName == "경남" && addr == "경상남도") {
                    list.push(data);
                } else if(sidoName == "전북" && (addr == "전라북도" || addr == "전북")) {
                    list.push(data);
                } else if(sidoName == "전남" && addr == "전라남도") {
                    list.push(data);
                } else if(addr.indexOf(sidoName) > -1) {
                    list.push(data);
                }
            }
        }
        for(i = 0, len = list.length; i < len; i++) {
            var wlobsInfo = list[i];
            var obsrvnInfo = obsrvnList.find(function(item) {
                return item.wlobscd == wlobsInfo.wlobscd;
            });
            wlobsInfo["wl"] = obsrvnInfo ? obsrvnInfo.wl : "-";
        }
        console.log("sidoName: " + sidoName + " / 측정소 수: " + list.length, list);
    }
    
    // 농업기상 관측지점 정보
    , getObsrSpotList: function() {
        $.ajax({
            url: "/front/api/getObsrSpotList.do"
            , type: "GET"
            , data: {doSeCode: 1} 
            , cache: false
            , success: function(result) {
                try {
                    console.log(result);
                } catch {
                    console.log("서비스 제공상태가 원할하지 않습니다.");
                }
            }
        });
    }
    
    // 농업기상 관측 정보 조회(시간)
    , getWeatherTimeList: function(obsrSpotCode) {
        $.ajax({
            url: "/front/api/getWeatherTimeList.do"
            , type: "GET"
            , data: {dateTime: $.datepicker.formatDate("yy-mm-dd", new Date()), obsrSpotCode: obsrSpotCode} 
            , cache: false
            , success: function(result) {
                try {
                    if(result.response) {
                        apiManager.setWeatherTimeList(result.response.body);
                    } else {
                        console.log("서비스 제공상태가 원할하지 않습니다.");
                    }
                } catch {
                    console.log("서비스 제공상태가 원할하지 않습니다.");
                }
            }
        });
    }
    
    , setWeatherTimeList: function(data) {
        console.log("::::::::::::::::::최근 농업기상정보 시작::::::::::::::::::");
        var dataList = data.items.item;
        var len = data.total_Count;
        var data;
        for(var i = data.total_Count - 3; i < len; i++) {
            data = dataList[i];
            console.log(data.date + ": 기온 - " + data.temp + "℃ / 습도 -  " + data.hum + "% / 강수량 - " + data.rain + "mm / 일조시간 - " + data.sun_Time);
        }
        console.log("::::::::::::::::::최근 농업기상정보 끝::::::::::::::::::");
    }
    
    // 농업기상 관측 정보 조회(일)
    , getWeatherMonDayList: function(obsrSpotCode) {
        $.ajax({
            url: "/front/api/getWeatherMonDayList.do"
            , type: "GET"
            , data: {searchYear: $.datepicker.formatDate("yy", new Date()), searchMonth: $.datepicker.formatDate("mm", new Date()), obsrSpotCode: obsrSpotCode} 
            , cache: false
            , success: function(result) {
                try {
                    if(result.response) {
                        apiManager.setWeatherMonDayList(result.response.body);
                    } else {
                        console.log("서비스 제공상태가 원할하지 않습니다.");                                                                                
                    }
                } catch {
                    console.log("서비스 제공상태가 원할하지 않습니다.");
                }
            }
        });
    }
    
    , setWeatherMonDayList: function(data) {
        console.log("::::::::::::::::::과거 농업기상정보 시작::::::::::::::::::");
        var dataList = data.items.item;
        var len = data.total_Count;
        var data;
        for(var i = data.total_Count - 5; i < len; i++) {
            data = dataList[i];
            console.log(data.date + ": 평균기온 - " + data.temp + "℃ / 최고기온 -  " + data.max_Temp + "℃ / 최저기온 - " + data.temp + "℃ / 일조시간 - " + data.sun_Time + " / 일강수량 - " + data.rain + "mm");
        }
        console.log("::::::::::::::::::과거 농업기상정보 끝::::::::::::::::::");
    }
    
    // 전기자동차 충전소 정보
    , getChargerInfo: function(zcode, zscode) {
        $.ajax({
            url: "/front/api/getChargerInfo.do"
            , type: "GET"
            , data: {zcode: zcode, zscode: zscode} 
            , cache: false
            , success: function(result) {
                try {
                    console.log(result);
                    if(result.resultCode && result.resultCode == "00") {
                        apiManager.setChargerInfo(result);
                    } else {
                        console.log("서비스 제공상태가 원할하지 않습니다.");                                                                                
                    }
                } catch {
                    console.log("서비스 제공상태가 원할하지 않습니다.");
                }
            }
        });
    }
    
    , setChargerInfo: function(data) {
        console.log("::::::::::::::::::전기자동차 충전소 정보 시작::::::::::::::::::");
        var dataList = data.items.item;
        var len = data.totalCount;
        var data, type, stat;
        for(var i = 0; i < len; i++) {
            data = dataList[i];
            type = data.chgerType == "01" ? "DC차데모" : data.chgerType == "02" ? "AC완속" : data.chgerType == "03" ? "DC차데모 + AC3상" :
                     data.chgerType == "04" ? "DC콤보" : data.chgerType == "05" ? "DC차데모 + DC콤보" : data.chgerType == "06" ? "DC차데모 + AC3상 + DC콤보" : 
                     data.chgerType == "07" ? "AC3상" : "-";
            stat = data.stat == "1" ? "통신이상" : data.stat == "2" ? "충전대기" : data.stat == "3" ? "충전중" : data.stat == "4" ? "운영중지" :
                    data.stat == "5" ? "점검중" : data.stat == "9" ? "상태미확인" : "-";
            console.log("충전소명: " + stat + " 충전소타입 - " + type + " / 충전기상태 - " + stat + " / 주소 - " + data.addr);
        }
        console.log("::::::::::::::::::전기자동차 충전소 정보 끝::::::::::::::::::");
    }
};