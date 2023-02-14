const getComparisonTemplate = () => `
    <section class="oilInfoArea">
        <section class="oilInfoArea__background">
            <div class="oilInfoArea__tabArea">
                <div class="oilInfoArea__tab"><a href="/userDetail" data-link>프로필 수정</a></div>
                <div class="oilInfoArea__tab"><a href="/inputOilInfo" data-link>주유 기록 입력</a></div>
                <div class="oilInfoArea__tab oilInfoArea__choosedTab"><a href="/comparison" data-link>이번 달 분석</a></div>
                <div class="oilInfoArea__tab"><a href="/chart" data-link>월별 비교</a></div>
                <div class="oilInfoArea__tab"><a href="/history" data-link>주유 기록 열람</a></div>
            </div>
            <div class="oilInfoArea__contentArea">
                <h1 class="oilInfoArea__contentTitle">
                    <svg xmlns="http://www.w3.org/2000/svg" width="55" height="55" viewBox="0 0 70 70" fill="none">
                        <path d="M52.5 11.6668H49.5833V8.75016C49.5833 7.97661 49.276 7.23475 48.7291 6.68777C48.1821 6.14079 47.4402 5.8335 46.6667 5.8335C45.8931 5.8335 45.1513 6.14079 44.6043 6.68777C44.0573 7.23475 43.75 7.97661 43.75 8.75016V11.6668H26.25V8.75016C26.25 7.97661 25.9427 7.23475 25.3957 6.68777C24.8487 6.14079 24.1069 5.8335 23.3333 5.8335C22.5598 5.8335 21.8179 6.14079 21.2709 6.68777C20.724 7.23475 20.4167 7.97661 20.4167 8.75016V11.6668H17.5C15.1794 11.6668 12.9538 12.5887 11.3128 14.2296C9.67187 15.8706 8.75 18.0962 8.75 20.4168V55.4168C8.75 57.7375 9.67187 59.9631 11.3128 61.604C12.9538 63.245 15.1794 64.1668 17.5 64.1668H52.5C54.8206 64.1668 57.0462 63.245 58.6872 61.604C60.3281 59.9631 61.25 57.7375 61.25 55.4168V20.4168C61.25 18.0962 60.3281 15.8706 58.6872 14.2296C57.0462 12.5887 54.8206 11.6668 52.5 11.6668ZM17.5 17.5002H20.4167V20.4168C20.4167 21.1904 20.724 21.9322 21.2709 22.4792C21.8179 23.0262 22.5598 23.3335 23.3333 23.3335C24.1069 23.3335 24.8487 23.0262 25.3957 22.4792C25.9427 21.9322 26.25 21.1904 26.25 20.4168V17.5002H43.75V20.4168C43.75 21.1904 44.0573 21.9322 44.6043 22.4792C45.1513 23.0262 45.8931 23.3335 46.6667 23.3335C47.4402 23.3335 48.1821 23.0262 48.7291 22.4792C49.276 21.9322 49.5833 21.1904 49.5833 20.4168V17.5002H52.5C53.2735 17.5002 54.0154 17.8075 54.5624 18.3544C55.1094 18.9014 55.4167 19.6433 55.4167 20.4168V32.0835H14.5833V20.4168C14.5833 19.6433 14.8906 18.9014 15.4376 18.3544C15.9846 17.8075 16.7265 17.5002 17.5 17.5002ZM52.5 58.3335H17.5C16.7265 58.3335 15.9846 58.0262 15.4376 57.4792C14.8906 56.9322 14.5833 56.1904 14.5833 55.4168V37.9168H55.4167V55.4168C55.4167 56.1904 55.1094 56.9322 54.5624 57.4792C54.0154 58.0262 53.2735 58.3335 52.5 58.3335Z" fill="#14BD7E"/>
                        <path d="M23.3332 49.5833C24.944 49.5833 26.2498 48.2775 26.2498 46.6667C26.2498 45.0558 24.944 43.75 23.3332 43.75C21.7223 43.75 20.4165 45.0558 20.4165 46.6667C20.4165 48.2775 21.7223 49.5833 23.3332 49.5833Z" fill="#14BD7E"/>
                        <path d="M46.6668 43.75H35.0002C34.2266 43.75 33.4847 44.0573 32.9378 44.6043C32.3908 45.1513 32.0835 45.8931 32.0835 46.6667C32.0835 47.4402 32.3908 48.1821 32.9378 48.7291C33.4847 49.276 34.2266 49.5833 35.0002 49.5833H46.6668C47.4404 49.5833 48.1822 49.276 48.7292 48.7291C49.2762 48.1821 49.5835 47.4402 49.5835 46.6667C49.5835 45.8931 49.2762 45.1513 48.7292 44.6043C48.1822 44.0573 47.4404 43.75 46.6668 43.75Z" fill="#14BD7E"/>
                    </svg>
                    <span>이번 달 분석</span>
                </h1>
                <div class="oilInfoArea__contentBody">
                    <div class="oilInfoArea__compareArea">
                        <h2 class="oilInfoArea__compareTitle">이번 달은 <span>커피</span> 만큼의 돈을 절약했어요!</h2>
                        <div class="oilInfoArea__compareContent">
                            <div class="oilInfoArea__boxArea">
                                <div class="oilInfoArea__compareMyBox">
                                    <h3><span>나</span>의 지출액</h3>
                                    <h1>329,000</h1>
                                </div>
                                <div class="oilInfoArea__compareMySaveBox">
                                    <h3><span>나</span>의 절약 금액</h3>
                                    <h1>+ 2,000</h1>
                                </div>
                                <div class="oilInfoArea__otherText">
                                    <h2>현재까지 주유액은 20대 남자 중  <span>상위 70%</span>  입니다.</h2>
                                </div>
                                <div class="oilInfoArea__compareAgeCommonBox">
                                    <h3><span>20대 남자</span> 평균 절약 금액</h3>
                                    <h1>300,000</h1>
                                </div>
                                <div class="oilInfoArea__chartBox">
                                    <div class="oilInfoArea__chartArea">
                                        <div class="oilInfoArea__myChart"></div>
                                        <div class="oilInfoArea__otherChart"></div>
                                    </div>
                                    <div class="oilInfoArea__chartValueTextArea">
                                        <span class="oilInfoArea__chartValueText--user"></span>
                                        <span class="oilInfoArea__chartValueText--common"></span>
                                    </div>
                                    <div class="oilInfoArea__chartNameArea">
                                        <span class="oilInfoArea__chartNameArea--name"></span>
                                        <span class="oilInfoArea__chartNameArea--info"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="oilInfoArea__compareImgBox">
                                <img src="./public/img/myProfile_Image/coffee.png" alt="비교 이미지">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </section>
`;

export { getComparisonTemplate }