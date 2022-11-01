import { useRef } from "react";
import { useIsLogin } from "../hooks/useIsLogin"

export default function FormChat({listMess,setMessage,chat,message}){
    const {isLogin} = useIsLogin()
    const textInput = useRef(null);
    const handleChat =()=>{
        chat()
        setMessage('')
    }
    

    return (
        <div class="border-t dark:border-gray-600">
                            <div class="lg:p-8 p-4 space-y-5  h-[600px] overflow-scroll">
                                {listMess?.map((mess,index)=>{
                                    if(mess.senderID == isLogin.id){
                                        return (
                                                <div class="flex lg:items-center flex-row-reverse">
                                                    <div class="w-14 h-14 rounded-full relative flex-shrink-0">
                                                        <img src="https://source.unsplash.com/random/?bakery,bake,1" alt="" class="absolute h-full rounded-full w-full"/>
                                                    </div>
                                                    <div class="text-white py-2 px-3 rounded bg-pink-600 relative h-full lg:mr-5 mr-2 lg:ml-20">
                                                        <p class="leading-6">{mess.messageBody}<i class="uil-grin-tongue-wink"></i> </p>
                                                        <div class="absolute w-3 h-3 top-3 -right-1 bg-pink-600 transform rotate-45"></div>
                                                    </div>
                                                </div>
                                        )
                                    }else{
                                        return (
                                            <div class="flex lg:items-center">
                                                <div class="w-14 h-14 rounded-full relative flex-shrink-0">
                                                    <img src="https://source.unsplash.com/random/?bakery" alt="" class="absolute h-full rounded-full w-full"/>
                                                </div>
                                                <div class="text-gray-700 py-2 px-3 rounded bg-gray-100 h-full relative lg:ml-5 ml-2 lg:mr-20 dark:bg-gray-700 dark:text-white">
                                                    <p class="leading-6">{mess.messageBody}<urna class="i uil-heart"></urna> <i class="uil-grin-tongue-wink"> </i> </p>
                                                    <div class="absolute w-3 h-3 top-3 -left-1 bg-gray-100 transform rotate-45 dark:bg-gray-700"></div>
                                                </div>
                                            </div>
                                        )
                                    }
                                })}
                                {/* 
                                 */}
                            </div>
                            <div class="border-t flex p-6 dark:border-gray-700">
                                <input refs={textInput} onKeyPress={e => {
                                    if(e.key === "Enter") {
                                        handleChat();
                                    }
                                }} value={message} onChange={e=>setMessage(e.target.value)} cols="1" rows="1" placeholder="Your Message.." class="border-0 flex-1 h-10 min-h-0 resize-none min-w-0 shadow-none dark:bg-transparent"></input>
                                <div class="flex h-full space-x-2">
                                    <button  onClick={handleChat} type="submit" class="bg-pink-600 font-semibold px-6 py-2 rounded-md text-white">Send</button>
                                </div>
                            </div>
                        </div>
    )
}