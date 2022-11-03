

const [username, setUsername] = useState('');

const header = {
    "Authorization": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodW5nIiwiZXhwIjoxNjY3NzYwMjUyLCJpYXQiOjE2NjcxNTU0NTJ9.SQwsadF4dslcwHy-n3eLgJOFa_fCsfn4cMJZoiSAIgA3uIfH-_EKO9GznO_uu-DmYEaJ1cma_2vB1M_pI1OfbA"
};

const connect = () => {
    let Sock = new SockJS('http://localhost:80/ws');
    stompClient = over(Sock);
    // stompClient.debug = null;
    stompClient.connect(header, onConnected, onError);
}

const onConnected = () => {
    stompClient.subscribe('/app/online', onMessageReceived, header);
    stompClient.subscribe(`/user/${username}/private`, onMessageReceived, header);
    stompClient.subscribe('/user/1/error', onMessageReceived, header);
    // userJoin();
}


const userJoin = () => {
    stompClient.send("/app/private-message", header, JSON.stringify({
        senderID: 1,
        receiverID: 67,
        massageBody: "Test"
    }));
}


const onMessageReceived = (payload) => {
    console.log(JSON.parse(payload.body))
}

