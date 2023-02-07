const isSamePWInputNodes = ($pwInput, $pwReInput) => $pwInput.value === $pwReInput;

const getGenderFromInput = ($genderInput) => {
    if($genderInput.value == "남자") return "M";
    else if($genderInput.value == "여자") return "F";
}

const getRegisterFetchBody = ($IDInput, $pwInput, $genderInput, $ageInput) => {
    return {
        "id": $IDInput.value,
        "password": $pwInput.value,
		"gender": getGenderFromInput($genderInput),
		"age": $ageInput.value
    }
}

export { isSamePWInputNodes }