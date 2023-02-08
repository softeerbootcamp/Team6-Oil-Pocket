import { addEvent, changeCSS } from "../common/function"
import { fetchRegisterID, fetchValidateID } from "./fetch";
import { getRegisterFetchBody, isSamePWInputs } from "./helperFunction";

const eventToTermOfUseBtn = ($container) => {
    const $termOfUseBtn = $container.querySelector(".registerArea__termOfUseContentBtn");
    const $termOfUseParagraph = $container.querySelector(".registerArea__termOfUseContent");
    const $termOfUseArea = $container.querySelector(".registerArea__termOfUse");

    addEvent($termOfUseBtn, [
        () => $termOfUseParagraph.innerHTML = ``,
        () => changeCSS($termOfUseParagraph, "height", "1vh"),
        () => changeCSS($termOfUseArea, "height", "12vh"),
        () => $termOfUseBtn.classList.add("clicked")
    ]);
}

const eventToSelects = ($container) => {
    const $selectAge = $container.querySelector("#registerArea__ageBtn");
    const $selectGender = $container.querySelector("#registerArea__genderBtn");

    addEvent($selectAge, [() => changeCSS($selectAge, "backgroundColor", "#14BD7E")], "change");
    addEvent($selectGender, [() => changeCSS($selectGender, "backgroundColor", "#14BD7E")], "change");
}

const eventToRegisterBtn = ($container) => {
    const $IDInput = $container.querySelector(".registerArea__IDArea > input")
    const $registerBtn = $container.querySelector(".registerBtn");
    const $selectAge = $container.querySelector("#registerArea__ageBtn");
    const $selectGender = $container.querySelector("#registerArea__genderBtn");
    const $pwInput = $container.querySelector("#registerArea__pwInput");
    const $pwReInput = $container.querySelector("#registerArea__pwReInput");

    addEvent($registerBtn, [() => {
        if($selectGender.value === "성별") {
            changeCSS($selectGender, "outline", "0.4vh solid red");
            setTimeout(() => changeCSS($selectGender, "outline", "0vh solid #14BD7E"), 1000);
            $selectGender.focus();
            return;
        }

        if($selectAge.value == "나이") {
            changeCSS($selectAge, "outline", "0.4vh solid red");
            setTimeout(() => changeCSS($selectAge, "outline", "0vh solid #14BD7E"), 1000);
            return;
        }

        if($pwInput.value === "") {
            changeCSS($pwInput, "outline", "0.4vh solid red");
            setTimeout(() => {
                changeCSS($pwInput, "outline", "0vh solid #14BD7E");
                $pwInput.focus();
            }, 1000);

            return ;
        }

        if($pwReInput.value === "") {
            changeCSS($pwReInput, "outline", "0.4vh solid red");
            setTimeout(() => {
                changeCSS($pwReInput, "outline", "0vh solid #14BD7E");
                $pwReInput.focus();
            }, 1000);

            return ;
        }

        if(!isSamePWInputs($pwInput, $pwReInput)) {
            $pwInput.focus();
            return;
        }

        const registerJSON = getRegisterFetchBody($IDInput, $pwInput, $selectGender, $selectAge);
        fetchRegisterID(registerJSON);
    }])
}

const eventToIDValidateBtn = ($registerContainer) => {
    const $IDValidateBtn = $registerContainer.querySelector(".IDValidateBtn");
    const $registerBtn = $registerContainer.querySelector(".registerBtn");
    const $IDInput = $registerContainer.querySelector(".registerArea__IDArea > input");

    addEvent($IDValidateBtn, [() => fetchValidateID($IDInput, $IDValidateBtn, $registerBtn)]);
}

export { eventToTermOfUseBtn, eventToIDValidateBtn, eventToSelects, eventToRegisterBtn }