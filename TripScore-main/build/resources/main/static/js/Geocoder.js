// src/main/resources/static/js/Geocoder.js

// IIFE 로 감싸서 전역 오염 방지
;(function() {
  // SDK 로드 완료 시 호출될 initMap 함수
  function initMap() {
    // 1) 지도 생성
    const map = new kakao.maps.Map(
      document.getElementById('map'),
      {
        center: new kakao.maps.LatLng(37.5665, 126.9780),
        level: 7
      }
    );

    // 2) 주소 → 좌표 변환 서비스
    const geocoder = new kakao.maps.services.Geocoder();

    // 3) Thymeleaf 가 바인딩한 주소 배열 (window.addresses)
    const addresses = window.addresses || [];

    // 4) 모든 마커를 포함할 LatLngBounds
    const bounds = new kakao.maps.LatLngBounds();

    addresses.forEach(addr => {
      geocoder.addressSearch(addr, (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
          const coords = new kakao.maps.LatLng(result[0].y, result[0].x);

          // 5) 마커 생성
          new kakao.maps.Marker({
            map: map,
            position: coords,
            title: addr
          });

          // 6) bounds 확장
          bounds.extend(coords);
          map.setBounds(bounds);
        }
      });
    });
  }

  // SDK 의 autoload=false 모드로 로드했다면 이렇게 실행
  kakao.maps.load(initMap);
})();
