const cityMap = {
  '경기': ['안성', '평택', '수원'],
  '충청': ['청주', '천안', '공주'],
  '강원': ['춘천', '강릉', '원주']
};

// 도를 선택했을 때 실행
function handleProvinceChange() {
  const provinceDropdown = document.getElementById('provinceDropdown');

  //선택된 province값 가져오기
  const selectedProvince = provinceDropdown.value;

  const cityDropdown = document.getElementById('cityDropdown');

  const select_province = document.getElementById('province');

  select_province.textContent = selectedProvince || "도"; //province 버튼 이름 변경(알아볼라고)


  // 기존 옵션 초기화
  cityDropdown.innerHTML = '<option value="">시를 선택하세요</option>';

    //선택된 province값이 있는지 확인하고 cityMap(key, value)에서 key값에
    //선택된 province값 넣어서 배열의 값들 가져오기
  if (selectedProvince && cityMap[selectedProvince]) {
    cityMap[selectedProvince].forEach(city => {
      const option = document.createElement('option');
      option.value = city;
      option.textContent = city;
      cityDropdown.appendChild(option);
    });
  }
}

//시 안됐음 해야됨
function handleCityChange() {
  const cityDropdown = document.getElementById('cityDropdown');
  const selectedCity = cityDropdown.value;
  const select_city = document.getElementById('city');

  select_city.textContent = selectedCity || "시";
}