import { addEvent, _$ } from "../../common/function"
import { fetchModifyUserDetail } from "./fetch";
import { getGenderFromLabel, getAgeFromLabel } from "./helperFunction";

const eventToUserDetailModifyBtn = ($container) => {
    const $modifyBtn = _$(".oilInfoArea__profileBtn--register", $container);

    addEvent($modifyBtn, [
        () => {
            const modifiedGender = getGenderFromLabel($container);
            const modifiedAge = getAgeFromLabel($container);

            fetchModifyUserDetail(modifiedGender, modifiedAge, $container);
        }
    ])
}

export { eventToUserDetailModifyBtn }