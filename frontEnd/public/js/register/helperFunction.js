const setTargetDisabled = ($btn, disableOption) => $btn.disabled = disableOption;

const isSamePWInputs = ($pwInput, $pwReInput) => $pwInput.value === $pwReInput.value;

const getRegisterFetchBody = ($IDInput, $pwInput, $genderInput, $ageInput) => {
    return {
        "id": $IDInput.value,
        "password": $pwInput.value,
		"gender": $genderInput.innerHTML,
		"age": $ageInput.innerHTML
    }
}

export { setTargetDisabled, isSamePWInputs, getRegisterFetchBody }