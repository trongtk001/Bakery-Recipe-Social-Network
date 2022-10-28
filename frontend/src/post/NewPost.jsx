import React, { useEffect, useState } from "react";
import { Link, useHistory } from "react-router-dom";
import { useDispatch } from "react-redux";
import { FaTrashAlt } from "react-icons/fa";
import { Post } from "../store/actions/post.action";
import { storeImageToFireBase } from "../utils/storeImageToFirebase.";
import Load from "./../components/load/index";
import { useIsLogin } from "../hooks/useIsLogin";
import { NotificationContainer } from "react-notifications";
import axios from "axios";
import Step from "../components/post/step";

function NewPost() {
    const dispatch = useDispatch();
    const history = useHistory();
    const { loading } = useIsLogin();
    const [postBody, setPostBody] = useState("");
    const [name, setName] = useState("");
    const [tool, setTool] = useState([]);
    const [step, setStep] = useState([]);
    const [postImages, setPostImages] = useState([]);
    const [postVideos, setPostVideos] = useState([]);
    const [tool1, setTool1] = useState({});
    const [tool2, setTool2] = useState({});
    const [recipe, setRecipe] = useState({});
    const [selectedFile, setSelectedFile] = useState();
    const [isLoading, setIsLoading] = useState(false);
    const [post, setPost] = useState({
        postBody,
        steps: [],
    });


    const [dataIng, setDataIng] = useState([]);

    useEffect(async () => {
        const res = await axios.get(`${process.env.REACT_APP_API_URL + "/ingredient"}`);
        if (res.data) {
            setDataIng(res.data);
            console.log(dataIng);
        }
    }, []);

    useEffect(
        () => {
            const uploadImage = async () => {
                setIsLoading(true);
                if (!selectedFile) {
                    setIsLoading(false);
                    return;
                }
                const { isSuccess, imageUrl, message } = await storeImageToFireBase(selectedFile);
                if (isSuccess) {
                    setPostImages([...postImages, { image: imageUrl }]);
                    setIsLoading(false);
                    return imageUrl;
                } else {
                    console.log(message);
                }
                setIsLoading(false);
            };
            uploadImage();
        },
        // eslint-disable-next-line
        [selectedFile]
    );

    const onSelectFile = (e) => {
        if (!e.target.files || e.target.files.length === 0) {
            setSelectedFile(undefined);
            return;
        }
        setSelectedFile(e.target.files[0]);
    };

    const handleChange = (event) => {
        const { value, name } = event.target;
        setRecipe({
            ...recipe,
            postImages,
            [name]: value,
        });
    };

    const handleChangeTool1 = (e) => {
        const { value, name } = e.target;
        setTool1({
            ...tool1,
            [name]: value,
        });
    };
    const handleChangeTool2 = (e) => {
        const { value, name } = e.target;
        setTool2({
            ...tool2,
            [name]: value,
        });
    };

    const deleteImage = (id) => {
        setPostImages(postImages.filter((elm, index) => index !== id));
    };
    const clickSubmit = (event) => {
        event.preventDefault();
        addStep();
        dispatch(Post({ postBody, recipe: { ...post }, tool, history }));
    };

    const handleDelItemTool2 = (index) => {
        setPost({ postBody, steps: post.steps.filter((item, i) => i !== index) });
    };

    const handleDelItemTool = (index) => {
        setTool(tool.filter((item, i) => i !== index));
    };

    const addStep = () => {
        setPost({ ...post, steps: [...post.steps, { tool, recipe }], postBody });
        setRecipe({
            description: "",
            name: "",
        });
        setPostImages([]);
        setSelectedFile();

        console.log(post);
    };

    const addTool1 = () => {
        setTool([...tool, tool1]);
        setTool1({});
    };
    const addTool2 = () => {
        setTool([...tool, tool2]);
        setTool2({});
    };

    return (
        <div className="container m-auto">
            <Step step={step} setStep={setStep} />
            <h1 className="text-2xl leading-none text-gray-900 tracking-tight mt-3">Create A New Post</h1>
            <div className="w-full ">
                <div className="mt-10">
                    <label htmlFor>Post body</label>
                    <input type="text" name="postBody" placeholder="Your post body.." className="shadow-none bg-white rounded-md " onChange={(e) => setPostBody(e.target.value)} value={postBody} />
                </div>
            </div>
            <div className="w-full ">
                <div className="mt-10">
                    <label htmlFor>Name</label>
                    <input type="text" name="name" placeholder="Your recipe name.." className="shadow-none bg-white rounded-md " onChange={(e) => setName(e.target.value)} value={name} />
                </div>
            </div>
            <div className="w-full mt-10">
                
            </div>
            <div noValidate>
                <div className="grid lg:grid-cols-3 mt-12 gap-8 rounded-md lg:shadow-lg shadow lg:p-6 p-4 bg-white dark:bg-gray-900 ">
                    <div>
                        <div className="px-4 py-3 -mx-5 -mt-4 mb-5 border-b">
                            <h4>Profile Photo</h4>
                        </div>
                        <div className="flex-center text-center dark:text-gray-300  bg-white dark:bg-gray-900">
                            <div className="flex flex-col choose-upload text-center">
                                {postImages.length > 0 ? (
                                    postImages.map((postImages, index) => (
                                        <div
                                            className="bg-gray-400 border-2 border-dashed flex flex-col h-56 items-center justify-center relative w-full rounded-lg "
                                            style={{
                                                backgroundImage: `url(${postImages.image})`,
                                            }}
                                            key={index}
                                        >
                                            <Link
                                                to="#"
                                                className="hover:text-red-400 text-red-500"
                                                style={{
                                                    position: "absolute",
                                                    right: "10px",
                                                    top: "10px",
                                                }}
                                                onClick={() => deleteImage(index)}
                                            >
                                                <FaTrashAlt />
                                            </Link>
                                        </div>
                                    ))
                                ) : (
                                    <div className=" border-2 border-dashed flex flex-col h-56 items-center justify-center relative w-full rounded-lg bg-gray-100 dark:bg-gray-900">
                                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" className="w-12">
                                            <path d="M5.5 13a3.5 3.5 0 01-.369-6.98 4 4 0 117.753-1.977A4.5 4.5 0 1113.5 13H11V9.413l1.293 1.293a1 1 0 001.414-1.414l-3-3a1 1 0 00-1.414 0l-3 3a1 1 0 001.414 1.414L9 9.414V13H5.5z" />
                                            <path d="M9 13h2v5a1 1 0 11-2 0v-5z" />
                                        </svg>
                                    </div>
                                )}
                                <p className="my-3 leading-6">
                                    Do you have a photo wants to share us <br /> please upload her ..
                                </p>
                                <div uk-form-custom className="uk-form-custom">
                                    {isLoading ? (
                                        <>
                                            <button
                                                type="button"
                                                disabled
                                                style={{
                                                    opacity: ".4",
                                                    display: "inline-flex",
                                                    alignItems: "center",
                                                    height: "40px",
                                                }}
                                                className="button soft-warning small"
                                            >
                                                Choose file <Load isSmall={true} />
                                            </button>
                                        </>
                                    ) : (
                                        <>
                                            <input
                                                onChange={onSelectFile}
                                                type="file"
                                                accept="image/*"
                                                className="shadow-none bg-dark-100"
                                                // value={changeValueInput}
                                            />
                                            <button type="button" className="button soft-warning small">
                                                Choose file
                                            </button>
                                        </>
                                    )}
                                </div>
                            </div>
                        </div>
                        <div className="bg-white dark:bg-gray-900 px-4 py-3 -mx-5 -mb-4 mt-5 border-t text-sm dark:border-gray-500 dark:text-gray-500">Your Photo size Must be Maxmium 999MB</div>
                    </div>
                    <div className="bg-white dark:bg-gray-900 col-span-2 ">
                        <div className="grid grid-cols-2 gap-3 ">
                            <>
                                <div className="col-span-2">
                                    <label htmlFor> Tên Bước</label>
                                    <input type="text" name="name" placeholder="name post.." className="shadow-none bg-gray-100" onChange={handleChange} value={recipe.name} />
                                </div>
                                <div className="col-span-2">
                                    <label htmlFor> Nguyên liệu</label>
                                    <div className="flex gap-2  flex-wrap">
                                        {tool?.map((item, index) => {
                                            return (
                                                <div onClick={() => handleDelItemTool(index)} key={index} className="inline-block ">
                                                    <div className="flex gap-2 items-center  px-5 py-1 border rounded-full ">
                                                        <span>{item.tool}</span> -<span>{item.unit}</span>
                                                        <div className="w-3 h-3 pb-4 rounded-full bg-dark-100 hover:opacity-30 !text-dark-400">
                                                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512">
                                                                <path d="M310.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L160 210.7 54.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L114.7 256 9.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L160 301.3 265.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L205.3 256 310.6 150.6z" />
                                                            </svg>
                                                        </div>
                                                    </div>
                                                </div>
                                            );
                                        })}
                                    </div>

                                    <div className="flex gap-4 flex-1">
                                        <input
                                            type="text"
                                            name="tool"
                                            placeholder="Your tool.."
                                            className="shadow-none bg-gray-100 mt-4 w-full "
                                            // onKeyDown={handleChangeTool}
                                            onChange={handleChangeTool1}
                                        />
                                        <div className="flex gap-4">
                                            <input type="text" name="unit" placeholder="Unit.." className="shadow-none bg-gray-100 mt-4 p-4 flex-1" onChange={handleChangeTool1} />
                                            <button onClick={addTool1} className="button bg-blue-700 mt-4">
                                                Add
                                            </button>
                                        </div>
                                    </div>
                                    <div className="flex gap-4 flex-1">
                                        <select onChange={handleChangeTool2} name="tool" className="w-full mt-4 px-4 bg-gray-100">
                                            {dataIng?.map((item, index) => {
                                                return (
                                                    <option key={index} value={item.ingredients}>
                                                        {item.ingredients}
                                                    </option>
                                                );
                                            })}
                                        </select>
                                        <div className="flex gap-4">
                                            <input
                                                onChange={handleChangeTool2}
                                                type="text"
                                                name="unit"
                                                placeholder="Unit.."
                                                className="shadow-none bg-gray-100 mt-4 p-4 flex-1 "
                                                // onKeyDown={handleChangeTool}
                                            />
                                            <button onClick={addTool2} className="button bg-blue-700 mt-4 text-center">
                                                Add
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-span-2">
                                    <label htmlFor="about">Content Post</label>
                                    <textarea
                                        id="body"
                                        name="description"
                                        className="shadow-none bg-gray-100 p-4"
                                        placeholder="Your description.."
                                        onChange={handleChange}
                                        value={recipe.description}
                                    />
                                </div>
                            </>
                        </div>
                    </div>
                </div>

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
                    <button onClick={addStep} className="button bg-blue-700">
                        Add Step
                    </button>
                </div>
            </div>
            <NotificationContainer />
        </div>
    );
}

export default NewPost;
