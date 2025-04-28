// src/main/resources/static/js/GeocoderSearch.js
;(function() {
  let map, geocoder, marker, infowindow;

  // 지도 생성 및 검색 로직 초기화 함수
  function initMapAndSearch() {
    // 1) 지도 생성
    map = new kakao.maps.Map(
      document.getElementById('map'),
      {
        center: new kakao.maps.LatLng(37.5665, 126.9780),
        level: 4
      }
    );

    // 2) Geocoder 서비스
    geocoder = new kakao.maps.services.Geocoder();

    // 3) 마커와 인포윈도우를 미리 생성 (재사용)
    marker = new kakao.maps.Marker({ map });
    infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

    // 4) 검색 버튼 이벤트 바인딩
    document.getElementById('searchButton').addEventListener('click', function(e) {
      e.preventDefault();
      const address = document.getElementById('searchKeyword').value.trim();
      if (!address) {
        alert('검색어를 입력해주세요.');
        return;
      }

      // 5) 주소 검색
      geocoder.addressSearch(address, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
          const loc = new kakao.maps.LatLng(result[0].y, result[0].x);

          // 마커 업데이트
          marker.setPosition(loc);
          marker.setMap(map);

          // 인포윈도우 열기
          infowindow.setContent(`<div style="padding:6px;text-align:center;">${address}</div>`);
          infowindow.open(map, marker);

          // 지도 중심 이동
          map.setCenter(loc);

          // 6) 폼 필드에 주소 자동 입력
          document.getElementById('lodging').value = address;
        } else {
          alert('주소를 찾을 수 없습니다.');
        }
      });
    });
  }

  // SDK가 이미 로드됐으면 즉시, 아니면 스크립트 load 이벤트 후 초기화
  if (window.kakao && kakao.maps && kakao.maps.load) {
    kakao.maps.load(initMapAndSearch);
  } else {
    const sdkScript = document.querySelector('script[src*="dapi.kakao.com"]');
    if (sdkScript) {
      sdkScript.addEventListener('load', function() {
        kakao.maps.load(initMapAndSearch);
      });
    } else {
      console.error('Kakao SDK script tag not found. Please check your HTML.');
    }
  }
})();
