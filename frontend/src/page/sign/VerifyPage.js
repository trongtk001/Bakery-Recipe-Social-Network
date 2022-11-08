import React from "react";

import { useHistory, useParams } from "react-router-dom";
import callApi from "../../utils/api/callApi";

function VerifyPage() {

    const { string } = useParams();
    const history = useHistory();
    console.log(string)

    const handleSubmit = () => {
        const code = string.split("\\");
        console.log(code)
        if (code[0] && code[1])
            callApi(`authentication/register/verify/?code=${code[0]}&username=${code[1]}`, "GET").then(res => {
                history.push("/SignIn");
            }).catch(err => console.log(err))
    }

    return (<>
        <div className="flex justify-center">
            <p className="px-6 py-3 bg-white text-pink-500 text-center">Verify Your Account</p>
            <button className="bg-pink-500 pink-500 px-6 py-3 rounded-md shadow text-white" onClick={handleSubmit}>Verify</button>
        </div>
    </>);
}

export default VerifyPage;