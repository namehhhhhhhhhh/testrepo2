import React, { useState, useEffect } from 'react'


export default function LoginForm() {
    const [captcha, setCaptcha] = useState({});
    const [answers, setAnswers] = useState([]);
    useEffect(() => {
        fetchCaptcha();
    }, []);


    const fetchCaptcha = async () => {
        const bufferSize = 10;
        const index = Math.floor(Math.random() * bufferSize);

        const api = 'http://localhost:5002/captcha?id=' + index;

        const data = await fetch(api).then((res) => res.json())
        // console.log(data);
        setCaptcha(data);
        setAnswers([data.ans1, data.ans2, 'pizza', 'rifle', 'alien', 'pasta']);
        // .then((data) => setCaptcha(data));
        // .then((data) => console.log(data));

    };

    const reloadCaptchaFromButton = (e) => {
        e.preventDefault();
        clearOptions();
        fetchCaptcha();
    };

    const reloadCaptcha = () => {
        clearOptions();
        fetchCaptcha();
    };

    const evaluateCaptcha = (e) => {
        e.preventDefault();

        const options = Array.from(document.getElementsByClassName('option'));
        console.log(options);
        let selectedAnswers = options.filter(ans => ans.checked === true);

        console.log(selectedAnswers);
        if (selectedAnswers.length === 2) {
            let answer1 = selectedAnswers[0].nextSibling.innerHTML;
            let answer2 = selectedAnswers[1].nextSibling.innerHTML;

            if ((answers[0] === answer1 && answers[1] === answer2) || (answers[1] === answer1 && answers[0] === answer2)) {
                window.location.href = 'login/success';
            } else {
                reloadCaptcha();
            }
        } else {
            reloadCaptcha();
        }

    };

    const clearOptions = () => {
        const options = Array.from(document.getElementsByClassName('option'));
        const selectedAnswers = options.filter(ans => ans.checked);
        selectedAnswers.forEach((ans) => ans.checked = false);

    }

    function shuffle(array) {
        let currentIndex = array.length, randomIndex;

        // While there remain elements to shuffle.
        while (currentIndex !== 0) {

            // Pick a remaining element.
            randomIndex = Math.floor(Math.random() * currentIndex);
            currentIndex--;

            // And swap it with the current element.
            [array[currentIndex], array[randomIndex]] = [
                array[randomIndex], array[currentIndex]];
        }

        return array;
    }


    return (
        <form className='captcha-form' onSubmit={evaluateCaptcha}>
            <img src={'data:image/png;base64,' + captcha.base64Url} alt='captcha' />
            <div className='answers-container'>
                {shuffle(answers.slice()).map((ans) => {
                    return (
                        <>
                            <div className={'ans-wrap ans-wrap-' + answers.indexOf(ans)}>
                                <input className='option' name={'option-' + answers.indexOf(ans)} type='checkbox' />
                                <label for={'option-' + answers.indexOf(ans)}>{ans}</label>
                            </div>
                        </>
                    )
                })}
            </div>
            <div className='btn-container'>
                <button className='refresh-btn' onClick={reloadCaptchaFromButton}>Refresh</button>
                <button type='submit'>Submit</button>
            </div>
        </form>
    );
}
