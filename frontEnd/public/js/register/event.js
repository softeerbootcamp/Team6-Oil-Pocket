import { addEvent } from "../../common/function"
import { fetchValidateID, fetchRegisterID } from "./fetch";

const eventToIDValidateBtn = ($registerContainer) => {
    const $IDValidateBtn = $registerContainer.querySelector(".IDValidateBtn");
    const $registerBtn = $registerContainer.querySelector(".registerBtn");
    const $IDInput = $registerContainer.querySelector(".registerArea__IDArea > input");

    addEvent($IDValidateBtn, [
        () => fetchValidateID($IDInput.value, $registerBtn)
    ]);
}

const eventToIDRegisterBtn = ($registerContainer) => {
    const $registerGenderBtn = $registerContainer.querySelector("#registerArea__genderBtn");
    const $registerAgeBtn = $registerContainer.querySelector("#registerArea__ageBtn");
    const $IDInput = $registerContainer.querySelector(".registerArea__IDArea > input");
    const $pwInput = $registerContainer.querySelector("#registerArea__pwInput");
    const $pwReInput = $registerContainer.querySelector("#registerArea__pwReInput");
}

export { eventToIDValidateBtn, eventToIDRegisterBtn }