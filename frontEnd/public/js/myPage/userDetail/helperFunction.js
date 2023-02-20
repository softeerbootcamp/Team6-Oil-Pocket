import { _$, _$_ALL } from "../../common/function"

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

    return age;
}

export { getGenderFromLabel, getAgeFromLabel }