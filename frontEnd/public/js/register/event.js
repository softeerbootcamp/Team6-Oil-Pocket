import { _$, _$_ALL, addEvent, changeCSS, changeArrayCSS } from "../common/utils"
import { fetchRegisterID, fetchValidateID } from "./fetch";
import { getRegisterFetchBody, isSamePWInputs } from "./helperFunction";

NodeList.prototype.forEach = Array.prototype.forEach;

const eventToTermOfUseBtn = ($container) => {
    const $termOfUseBtn = _$(".registerArea__termOfUseContentBtn", $container);
    const $termOfUseParagraph = _$(".registerArea__termOfUseContent", $container);
    const $termOfUseArea = _$(".registerArea__termOfUse", $container);

    addEvent($termOfUseBtn, [
        () => $termOfUseParagraph.innerHTML = ``,
        () => changeCSS($termOfUseParagraph, "height", "1vh"),
        () => changeCSS($termOfUseArea, "height", "12vh"),
        () => $termOfUseBtn.classList.add("clicked")
    ]);
}

const eventToSelects = ($container) => {
    const $selectgender = _$(".registerArea__genderTitle", $container);
    const $selectgenderValues = _$_ALL(".registerArea__genderValue", $container);
    const $selectAge = _$(".registerArea__ageTitle", $container);
    const $selectAgeValues = _$_ALL(".registerArea__ageValue", $container);

    addEvent($selectgender, [
        () => $selectgenderValues.forEach(($genderValue, index) => {
            changeCSS($genderValue, "outline", "0.2vh solid #14BD72");
            changeCSS($genderValue, "top", `${(index + 1) * 108}%`) 
        })
    ]);

    addEvent($selectAge, [
        () => $selectAgeValues.forEach(($ageValue, index) => {
            changeCSS($ageValue, "outline", "0.2vh solid #14BD72");
            changeCSS($ageValue, "top", `${(index + 1) * 108}%`);
        })
    ]);
}

const eventToSelectValues = ($container) => {
    const $genderTitle = _$(".registerArea__genderTitle", $container);
    const $genderValues = _$_ALL(".registerArea__genderValue", $container);
    const $ageTitle = _$(".registerArea__ageTitle", $container);
    const $ageValues = _$_ALL(".registerArea__ageValue", $container);

    $genderValues.forEach(($genderValue) => addEvent($genderValue, [
        () => $genderTitle.innerHTML = $genderValue.innerHTML,
        () => changeCSS($genderTitle, "backgroundColor", "#14BD72"),
        () => changeArrayCSS($genderValues, "top", 0)
    ]));

    $ageValues.forEach(($ageValue) => addEvent($ageValue, [
        () => $ageTitle.innerHTML = $ageValue.innerHTML,
        () => changeCSS($ageTitle, "backgroundColor", "#14BD72"),
        () => changeArrayCSS($ageValues, "top", 0)
    ]))
}

const eventToRegisterBtn = ($container) => {
    const $IDInput = _$(".registerArea__IDArea > input", $container);
    const $registerBtn = _$(".registerBtn", $container);
    const $selectAge = _$(".registerArea__ageTitle", $container);
    const $selectGender = _$(".registerArea__genderTitle", $container);
    const $pwInput = _$("#registerArea__pwInput", $container);
    const $pwReInput = _$("#registerArea__pwReInput", $container);

    addEvent($registerBtn, [() => {
        if($selectGender.innerHTML === "성별") {
            changeCSS($selectGender, "outline", "0.4vh solid red");
            setTimeout(() => changeCSS($selectGender, "outline", "0vh solid #14BD7E"), 1000);
            $selectGender.focus();
            return;
        }

        if($selectAge.innerHTML == "나이") {
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
    const $IDValidateBtn = _$(".IDValidateBtn", $registerContainer);
    const $registerBtn = _$(".registerBtn", $registerContainer);
    const $IDInput = _$(".registerArea__IDArea > input", $registerContainer);

    addEvent($IDValidateBtn, [() => fetchValidateID($IDInput, $IDValidateBtn, $registerBtn)]);
}

export { eventToTermOfUseBtn, eventToIDValidateBtn, eventToSelects, eventToRegisterBtn, eventToSelectValues }