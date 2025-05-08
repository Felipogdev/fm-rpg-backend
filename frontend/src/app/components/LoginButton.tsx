'use client';

import { Button } from "@/components/ui/button";

export default function LoginButton() {
    const loginWithPopup = () => {
        const popup = window.open(
            'http://localhost:8080/oauth2/authorization/google',
            'oauth2-login',
            'width=500,height=600'
        );

        const handleMessage = (event: MessageEvent) => {
            if (event.origin !== 'http://localhost:8080') return;
        };

        window.addEventListener('message', handleMessage);
    };

    return (
        <Button className="ml-auto bg-red-800 p-2 text-white rounded" onClick={loginWithPopup}>
            Login
        </Button>
    );
}
