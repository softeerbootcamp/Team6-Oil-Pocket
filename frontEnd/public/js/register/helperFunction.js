const setTargetDisabled = ($btn, disableOption) => $btn.disabled = disableOption;

const isSamePWInputs = ($pwInput, $pwReInput) => $pwInput.value === $pwReInput.value;

const getGenderValue = ($genderInput) => {
    let gender = "";
    $genderInput.innerHTML === "남자" ? gender = "M" : gender = "F";

    return gender;
}

const getRegisterFetchBody = ($IDInput, $pwInput, $genderInput, $ageInput) => {
    return {
        "id": $IDInput.value,
        "password": $pwInput.value,
		"gender": getGenderValue($genderInput),
		"age": $ageInput.innerHTML
    }
}

export { setTargetDisabled, isSamePWInputs, getRegisterFetchBody }