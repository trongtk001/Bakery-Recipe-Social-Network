import React, { useState } from "react";
import { Link, useHistory } from "react-router-dom";
import { useDispatch } from "react-redux";
import { Post } from "../store/actions/post.action";
import Load from "./../components/load/index";
import { useIsLogin } from "../hooks/useIsLogin";
import { NotificationContainer, NotificationManager } from "react-notifications";
import Step from "../components/post/Step";

function NewPost() {
    const dispatch = useDispatch();
    const history = useHistory();
    const { loading } = useIsLogin();
    const [postBody, setPostBody] = useState("");
    const [name, setName] = useState("");
    const [tool, setTool] = useState("");
    const [tools, setTools] = useState([]);
    const [steps, setSteps] = useState([]);

    const handleDelItemTool = (index) => {
        setTools((pre) => pre.filter((item, i) => i !== index));
    };

    const clickSubmit = (event) => {
        if (postBody && steps && tools) {
            const recipe = {
                name,
                steps,
                tool: tools,
            };
            console.log(JSON.stringify({ postBody, recipe }));
            event.preventDefault();
            dispatch(Post({ postBody, recipe, history }));
        } else {
            NotificationManager.warning("Your content must not empty", "Warning");
        }
    };

    return (
        <div className="container m-auto">
            <h1 className="text-2xl leading-none text-gray-900 tracking-tight mt-3">Create A New Post</h1>

            {/* postbody */}
            <div className="w-full ">
                <div className="mt-10">
                    <label htmlFor>Post body</label>
                    <input type="text" name="postBody" placeholder="Your post body.." className="shadow-none bg-white rounded-md " onChange={(e) => setPostBody(e.target.value)} value={postBody} />
                </div>
            </div>

            {/* recipe name */}
            <div className="w-full ">
                <div className="mt-10">
                    <label htmlFor>Name</label>
                    <input type="text" name="name" placeholder="Your recipe name.." className="shadow-none bg-white rounded-md " onChange={(e) => setName(e.target.value)} value={name} />
                </div>
            </div>

            {/* tool list */}
            <div className="w-full ">
                <div className="mt-10">
                    <label htmlFor>Tool</label>
                    <div className="flex gap-2  flex-wrap">
                        {tools?.map((item, index) => {
                            return (
                                <div onClick={() => handleDelItemTool(index)} key={index} className="inline-block ">
                                    <div className="flex gap-2 items-center px-5 py-1 border border-pink-600 text-pink-600 rounded-full ">
                                        <span>{item}</span>
                                        <div className="w-3 h-3 pb-4 rounded-full = hover:opacity-30 ">
                                            <svg xmlns="http://www.w3.org/2000/svg" fill="#db2222" viewBox="0 0 320 512">
                                                <path d="M310.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L160 210.7 54.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L114.7 256 9.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L160 301.3 265.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L205.3 256 310.6 150.6z" />
                                            </svg>
                                        </div>
                                    </div>
                                </div>
                            );
                        })}
                    </div>
                </div>
            </div>

            <div className="flex gap-4 flex-1">
                <input type="text" name="tool" placeholder="Your tool.." className="shadow-none bg-white mt-4 w-full" value={tool} onChange={(e) => setTool(e.target.value)} />
                <div className="flex gap-4">
                    <button
                        onClick={(e) => {
                            setTools((pre) => [...pre, tool]);
                            setTool("");
                        }}
                        className="button bg-blue-700 mt-4"
                    >
                        Add
                    </button>
                </div>
            </div>

            <Step steps={steps} setSteps={setSteps} />

            {/* Button */}
            <div className="bg-gray-10 p-6 pt-10 flex justify-end space-x-3">
                <Link to="#" className="p-2 px-4 rounded  text-red-500 ">
                    Cancel
                </Link>
                {loading ? (
                    <button
                        className="button bg-blue-700"
                        disabled
                        style={{
                            opacity: ".4",
                            display: "inline-flex",
                            alignItems: "center",
                            height: "40px",
                        }}
                    >
                        Create Post
                        <Load isSmall={true} />
                    </button>
                ) : (
                    <button onClick={clickSubmit} className="button bg-blue-700">
                        Create Post
                    </button>
                )}
            </div>

            <NotificationContainer />
        </div>
    );
}

export default NewPost;
