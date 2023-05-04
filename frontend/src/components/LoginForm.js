import React, { useEffect } from 'react'
import { useState } from 'react';

export default function LoginForm() {

    const evaluateLogin = async (e) => {
        e.preventDefault();

        const api = 'http://localhost:5002/api/v1/user/login';

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(
                {
                    username: e.target.username.value,
                    password: e.target.password.value
                }
            )
        };

        const data = await (await fetch(api, requestOptions)).json()

        if (data.st) {
            window.location.href = 'captcha';
        } else {
            window.location.href = 'login/fail';
        }
    };

    return (
        <form onSubmit={evaluateLogin} >
            <label htmlFor='username'>Insert username</label>
            <input type="text" name='username' required></input>
            <label htmlFor='password'>Insert password</label>
            <input type="password" name='password' required></input>
            <button type='submit'>Log In</button>
        </form >
    );
}
