const setTargetDisabled = ($btn, disableOption) => $btn.disabled = disableOption;

const isSamePWInputs = ($pwInput, $pwReInput) => $pwInput.value === $pwReInput.value;

const getGenderFromInput = ($genderInput) => {
    if($genderInput.value === "남") return "M";
    else if($genderInput.value === "여") return "F";
}

const getRegisterFetchBody = ($IDInput, $pwInput, $genderInput, $ageInput) => {
    return {
        "id": $IDInput.value,
        "password": $pwInput.value,
		"gender": getGenderFromInput($genderInput),
		"age": $ageInput.value
    }
}

export { setTargetDisabled, isSamePWInputs, getRegisterFetchBody }