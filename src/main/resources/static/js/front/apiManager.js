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
        console.log("+++++++++++++++++++++++++++실시간 대기정보 시작+++++++++++++++++++++++++++");
        console.log("sidoName: " + sidoName + " / tabIdx: " + tabIdx);
        if(displayWorkProgress(true)) {
            $.ajax({
                url: "/front/api/getCtprvnRltmMesureDnsty.do"
                , type: "GET"
                , data: {sidoName: sidoName} 
                , cache: false
                , success: function(result) {
                    if(result.response && result.response.header.resultCode == "00") {
                        main.setWidgetStatus("sucess", result.response.body.items[0].dataTime);
                        apiManager.setCtprvnRltmMesureDnsty(result.response.body, tabIdx, grid);
                    } else {
                        main.setWidgetStatus("error");
                    }
                }
            });
        }
    }
    , setCtprvnRltmMesureDnsty: function(data, tabIdx, grid) {
        for(var i = 0; i < data.totalCount; i++) {
            var info = data.items[i];
            var sidoName = info.sidoName;
            var stationName = info.stationName;
            var displayInfo = this.getRltmMesureDnstyInfo(tabIdx, info);
            
            $(grid).append('<tr><td>' + sidoName + '</td><td>' + stationName + '</td><td><span class="fc-level' + displayInfo.grade + '">' + displayInfo.value + '</span></td></tr>');
        }
        console.log("+++++++++++++++++++++++++++실시간 대기정보 끝+++++++++++++++++++++++++++");
    }
    
    // 오늘/내일/모레 예보
    , getMinuDustFrcstDspth: function(informCode) {
        $.ajax({
            url: "/front/api/getMinuDustFrcstDspth.do"
            , type: "GET"
            , data: {searchDate: $.datepicker.formatDate("yy-mm-dd", new Date()), informCode: informCode} 
            , cache: false
            , success: function(result) {
                if(result.response && result.response.header.resultCode == "00") {
                    apiManager.setMinuDustFrcstDspth(informCode, result.response.body);
                } else {
                    console.log("서비스 제공상태가 원할하지 않습니다.");
                }
            }
        });
    }
    
    , setMinuDustFrcstDspth: function(type, data) {
        console.log("::::::::::::::::::오늘/내일/모레 예보 시작::::::::::::::::::");
        console.log("구분: " + type, data);
        var now = new Date();
        var today = $.datepicker.formatDate("yy-mm-dd", now);
        var tomorrow = $.datepicker.formatDate("yy-mm-dd", new Date(now.setDate(now.getDate() + 1)));
        var afterTomorrow = $.datepicker.formatDate("yy-mm-dd", new Date(now.setDate(now.getDate() + 1)));
        var todayInfoList = "예보정보가 없습니다.", tomorrowInfoList = "예보정보가 없습니다.", afterTomorrowInfoList = "예보정보가 없습니다.";
        for(var i = 0, len = data.totalCount; i < len; i++) {
            var info = data.items[i];
            if(info.informData == today && todayInfoList == "예보정보가 없습니다.") {
                todayInfoList = info.informGrade.split(",");
            } else if(info.informData == tomorrow && tomorrowInfoList == "예보정보가 없습니다.") {
                tomorrowInfoList = info.informGrade.split(",");
            } else if(info.informData == afterTomorrow && afterTomorrowInfoList == "예보정보가 없습니다.") {
                afterTomorrowInfoList = info.informGrade.split(",");
            }
        }
        console.log("오늘: ", todayInfoList);
        console.log("내일: ", tomorrowInfoList);
        console.log("모레: ", afterTomorrowInfoList);
    }
    
    // 우리동네 대기정보
    , getMsrstnAcctoRltmMesureDnsty: function() {
        var stationName = !localStorage.getItem("nearMsrstn") ? "종로구" : localStorage.getItem("nearMsrstn");
        $.ajax({
            url: "/front/api/getMsrstnAcctoRltmMesureDnsty.do"
            , type: "GET"
            , data: {stationName: stationName} 
            , cache: false
            , success: function(result) {
                if(result.response && result.response.header.resultCode == "00") {
                    apiManager.setMsrstnAcctoRltmMesureDnsty(stationName, result.response.body);
                } else {
                    console.log("서비스 제공상태가 원할하지 않습니다.");
                }
            }
        });
    }
    
    , setMsrstnAcctoRltmMesureDnsty: function(stationName, data) {
        console.log("::::::::::::::::::우리동네 대기정보 시작::::::::::::::::::");
        console.log("측정소명: " + stationName, data);
        console.log("::::::::::::::::::우리동네 대기정보 끝::::::::::::::::::");
    }
    
    , onPostPopup: function() {
        new daum.Postcode({
            oncomplete: function(data) {
                apiManager.getTMStdrCrdnt(data.bname).then(function(response) {
                    var info, item;
                    for(var i = 0; i < response.totalCount; i++) {
                        item = response.items[i];
                        if(item.sggName == data.sigungu) {
                            info = item;
                            break;
                        }
                    }
                    apiManager.getNearbyMsrstnList(data, info);
                }).catch(function(error) {
                    console.log(error.message);
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
                        apiManager.getMsrstnAcctoRltmMesureDnsty();    
                    }
                } else {
                    console.log("서비스 제공상태가 원할하지 않습니다.");
                }
            }
        });
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
    
    // 수위정보
    , getWaterlevelList: function() {
        $.ajax({
            url: "/front/api/getWlobsList.do"
            , type: "GET"
            , data: {hydroType: "waterlevel"} 
            , cache: false
            , success: function(result) {
                console.log(result);
                try {
                    apiManager.getWlobsList(result.wlobsList.content, result.obsrvnList.content, "서울");
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