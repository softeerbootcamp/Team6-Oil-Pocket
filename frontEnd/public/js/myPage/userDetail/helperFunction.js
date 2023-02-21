import { _$, _$_ALL } from "../../common/utils"

NodeList.prototype.find = Array.prototype.find;

const getGenderFromLabel = ($container) => {
    const $maleLabel = _$("#radio--male", $container);
    if($maleLabel.checked) {
        return "M";
    }
    return "F";
}

const getAgeFromLabel = ($container) => {
    const $ageInputs = _$_ALL(".oilInfoArea__inputBoxArea--age > input", $container);
    let age = $ageInputs
        .find(($ageInput) => $ageInput.checked).value;

    if(age === "60대") {
        return "60대 이상"
    }

    return age;
}

export { getGenderFromLabel, getAgeFromLabel }