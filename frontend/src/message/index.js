/* eslint-disable */
import { useEffect, useState } from "react";
import FormChat from "./FormChat";
import FrChat from "./FrChat";
import Sidebar from "./Sidebar";

import { useIsLogin } from "../hooks/useIsLogin";
import { useDispatch, useSelector } from "react-redux";
import '../utils/prototype/array'
import { getMessage, messageSelector } from "../store/actions/message.action";
import { connect, disconect, subscribeOnline, subscribePrivateChat, subscribeError, joinChat, sendMess } from '../utils/message';

var stompClient;
export default function Message() {
    const { followers, friends } = useSelector((state) => state.user);
    const listMess = useSelector(messageSelector);

    const dispatch = useDispatch()
    const { isLogin } = useIsLogin();
    const [message, setMessage] = useState('');
    const [messages, setMessages] = useState([]);
    const [users, setUsers] = useState([]);
    const [active, setActive] = useState(null);
    const [usersActive, setUsersActive] = useState([]);
    const header = { "Authorization": `Bearer ${isLogin.token}` };

    const onMessageReceived = (payload) => {
        let a = JSON.parse(payload.body);
        dispatch({
            type: "RECEIVED_MESSAGE",
            payload: {
                memberID: a.senderID,
                messages: a
            }
        })

    }

    const onActive = (payload) => {
        console.log(JSON.parse(payload.body))
        setUsersActive(JSON.parse(payload.body));
    }

    const onError = (payload) => {
        console.log(payload);
    }

    const chat = () => {
        const messageObj = {
            senderID: isLogin.id,
            receiverID: active.id,
            messageBody: message
        }

        sendMess(header, messageObj, stompClient);
        dispatch({
            type: "SEND_MESSAGE",
            payload: {
                memberID: active.id,
                message: messageObj
            }
        })
        setMessage('');
    }

    useEffect(() => {
        stompClient || (stompClient = connect(header));
        stompClient.onConnect = () => {
            subscribeOnline(header, onActive, stompClient);
            subscribePrivateChat(header, isLogin.username, onMessageReceived, stompClient);
            subscribeError(header, isLogin.username, onError, stompClient);
            joinChat(header, stompClient);
        }
    }, [])

    useEffect(() => {
        const member = [...followers, ...friends].unique();
        setUsers(member);
        dispatch({ type: "CHAT_FRIENDS", payload: member });
        setActive(member[0])
        // eslint-disable-next-line
    }, [followers, friends]);

    useEffect(() => {
        if (isLogin.id && active?.id) {
            listMess.get(active.id)?.length ?
                setMessages(listMess.get(active.id)) : dispatch(getMessage(isLogin.id, active.id, 1, 8, setMessages))
            setMessages(listMess.get(active.id));
        }
        // eslint-disable-next-line
    }, [active, listMess]);


    return (
        <>
            <div className="container m-auto pt-5">
                <div className="lg:flex lg:shadow lg:bg-white lg:space-y-0 space-y-8 rounded-md lg:-mx-0 -mx-5 overflow-hidden lg:dark:bg-gray-800 h-full" >
                    <Sidebar users={users} setUsers={setUsers} active={active} setActive={setActive} listMess={listMess} ></Sidebar>
                    <div className="lg:w-8/12 bg-white dark:bg-gray-800">
                        <FrChat active={active} usersActive={usersActive} ></FrChat>
                        <FormChat active={active} message={message} setMessage={setMessage} chat={chat} listMess={messages}></FormChat>
                    </div>
                </div>
            </div>
        </>
    );
}
