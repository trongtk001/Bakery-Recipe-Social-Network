import { useEffect, useRef } from "react";
import { useIsLogin } from "../hooks/useIsLogin";
import defaltAvatar from '../images/avatar.png'

export default function FormChat({ active, listMess, setMessage, chat, message }) {
    const { isLogin, avatar } = useIsLogin();
    const textInput = useRef(null);
    const handleChat = () => {
        chat();
        setMessage(" ");
    };
    const scrollRef = useRef();

    useEffect(() => {
        scrollRef.current?.scrollIntoView();
    }, [listMess]);

    return (
        <div className="border-t dark:border-gray-600">
            <div className="lg:p-8 p-4 space-y-5  h-[600px] overflow-scroll">
                {listMess && listMess.map((mess, index) => {
                    if (mess.senderID === isLogin.id) {
                        return (
                            <div ref={scrollRef} key={index} className="flex lg:items-center flex-row-reverse">
                                <div className="w-14 h-14 rounded-full relative flex-shrink-0">
                                    <img src={avatar} alt="" className="absolute h-full rounded-full w-full" />
                                </div>
                                <div className="text-white py-2 px-3 rounded bg-pink-600 relative h-full lg:mr-5 mr-2 lg:ml-20">
                                    <p className="leading-6">
                                        {mess.messageBody}
                                        <i className="uil-grin-tongue-wink"></i>{" "}
                                    </p>
                                    <div className="absolute w-3 h-3 top-3 -right-1 bg-pink-600 transform rotate-45"></div>
                                </div>
                            </div>
                        );
                    } else {
                        return (
                            <div ref={scrollRef} key={index} className="flex lg:items-center">
                                <div className="w-14 h-14 rounded-full relative flex-shrink-0">
                                    <img src={active.avatar ? active.avatar : defaltAvatar} alt="" className="absolute h-full rounded-full w-full" />
                                </div>
                                <div className="text-gray-700 py-2 px-3 rounded bg-gray-100 h-full relative lg:ml-5 ml-2 lg:mr-20 dark:bg-gray-700 dark:text-white">
                                    <p className="leading-6">
                                        {mess.messageBody} <i className="uil-grin-tongue-wink"> </i>{" "}
                                    </p>
                                    <div className="absolute w-3 h-3 top-3 -left-1 bg-gray-100 transform rotate-45 dark:bg-gray-700"></div>
                                </div>
                            </div>
                        );
                    }
                })}
                {/*
                 */}
            </div>
            <div className="border-t flex p-6 dark:border-gray-700">
                <input
                    onKeyDown={(e) => {
                        if (e.key === "Enter") handleChat();
                    }}
                    refs={textInput}
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                    cols="1"
                    rows="1"
                    placeholder="Your Message.."
                    className="border-0 flex-1 h-10 min-h-0 resize-none min-w-0 shadow-none dark:bg-transparent"
                ></input>
                <div className="flex h-full space-x-2">
                    <button onClick={handleChat} type="submit" className="bg-pink-600 font-semibold px-6 py-2 rounded-md text-white">
                        Send
                    </button>
                </div>
            </div>
        </div>
    );
}
