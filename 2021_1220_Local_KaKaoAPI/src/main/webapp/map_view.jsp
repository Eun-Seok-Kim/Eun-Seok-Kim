<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

    <div id="map" style="width:800px;height:600px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5b6d329efe4e49fec7e98b8141c5ed07"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new daum.maps.LatLng(${param.latitude},${param.longitude}),
			level: 3
		};

		var map = new daum.maps.Map(container, options);
		
		// 지도를 클릭한 위치에 표출할 마커입니다
		var marker = new daum.maps.Marker({ 
		    // 지도 중심좌표에 마커를 생성합니다 
		    position: map.getCenter() 
		}); 
		// 지도에 마커를 표시합니다
		marker.setMap(map);
		
	</script>
</body>
</html>