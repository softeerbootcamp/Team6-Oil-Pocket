import { addEvent, _$ } from "../../common/utils"
import { fetchModifyUserDetail } from "./fetch";
import { getGenderFromLabel, getAgeFromLabel } from "./helperFunction";

const eventToUserDetailModifyBtn = ($container) => {
    const $modifyBtn = _$(".oilInfoArea__profileBtn--register", $container);

    addEvent($modifyBtn, [
        () => {
            const modifiedGender = getGenderFromLabel($container);
            const modifiedAge = getAgeFromLabel($container);
            console.log(modifiedGender, modifiedAge);
            fetchModifyUserDetail(modifiedGender, modifiedAge, $container);
        }
    ])
}

export { eventToUserDetailModifyBtn }