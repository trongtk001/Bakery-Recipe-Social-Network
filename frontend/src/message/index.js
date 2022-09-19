
import { useEffect, useState } from "react";
import FormChat from "./FormChat";
import FrChat from "./FrChat";
import Sidebar from "./Sidebar";
import SockJS from 'sockjs-client';
import { over } from 'stompjs';
import { useIsLogin } from "../hooks/useIsLogin";
import { useDispatch, useSelector } from "react-redux";
import '../utils/prototype/array'
import { getMessage, messageSelector } from "../store/actions/message.action";

var stompClient;

export default function Message() {

    const { user, followers, friends } = useSelector((state) => state.user);
    const isConnect = useSelector(state => state.message.isConnect);

    const dispatch = useDispatch()
    const { isLogin } = useIsLogin();
    const [message, setMessage] = useState([]);
    const [messages, setMessages] = useState([]);
    const [users, setUsers] = useState([]);
    const [active, setActive] = useState();
    const [usersActive, setUsersActive] = useState([]);
    const listMess = useSelector(messageSelector)

    const header = {
        "Authorization": `Bearer ${isLogin.token}`
    };



    const connect = () => {
        let Sock = new SockJS('http://35.198.245.224/api/ws');
        stompClient = over(Sock);
        // stompClient.debug = null;
        stompClient.connect(header, onConnected, onError);
    }

    const onConnected = () => {
        dispatch({ type: "CONNECT", payload: true });
        console.log("connected");
        stompClient.subscribe('/app/chat/online', onActive, header);
        stompClient.subscribe(`/user/${user.username}/queue/private`, onMessageReceived, header);
        stompClient.subscribe(`/user/${user.username}/info/error`, onError, header);
    }


    useEffect(() => {
        if (!isConnect) {
            connect();
        }
        // eslint-disable-next-line
    }, [isConnect])

    useEffect(() => {
        const member = [...followers, ...friends].unique();
        setUsers(member);
        setActive(member[0]);
        dispatch({ type: "CHAT_FRIENDS", payload: member });
        // eslint-disable-next-line
    }, [followers, friends]);

    useEffect(() => {
        if (isLogin?.id && active?.id) {
            listMess.get(active.id).length ?
                setMessages(listMess.get(active.id)) :
                dispatch(getMessage(isLogin.id, active.id, 1, 8, setMessages))
        }
        // eslint-disable-next-line
    }, [active, isLogin]);

    const chat = () => {
        stompClient.send("/app/private-message", header, JSON.stringify({
            senderID: isLogin.id,
            receiverID: active.id,
            messageBody: message
        }));
        setMessages(pre => [...pre, {
            senderID: isLogin.id,
            receiverID: active.id,
            messageBody: message
        }]);
        setMessage('');
    }

    const onMessageReceived = (payload) => {
        // setListMess(payload.body);
        console.log(true)
        let a = JSON.parse(payload.body);
        dispatch({
            type: "RECEIVED_MESSAGE",
            payload: {
                memberID: a.senderID,
                messages: a
            }
        })
        setMessages(pre => [...pre, a]);
    }

    const onActive = (payload) => {
        setUsersActive(JSON.parse(payload.body));
    }

    const onError = (err) => {
        console.log(err);
    }
    return (
        <>
            <div className="container m-auto pt-5">
                <div className="lg:flex lg:shadow lg:bg-white lg:space-y-0 space-y-8 rounded-md lg:-mx-0 -mx-5 overflow-hidden lg:dark:bg-gray-800 h-full" >
                    <Sidebar users={users} setUsers={setUsers} active={active} setActive={setActive}  ></Sidebar>
                    <div className="lg:w-8/12 bg-white dark:bg-gray-800">
                        <FrChat active={active} usersActive={usersActive} ></FrChat>
                        <FormChat message={message} setMessage={setMessage} chat={chat} listMess={messages}></FormChat>
                    </div>
                </div>
            </div>
        </>
    );
}
