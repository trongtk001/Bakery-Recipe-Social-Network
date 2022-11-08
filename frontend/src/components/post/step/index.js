import React, { useEffect, useState } from "react";

import { FaTrashAlt } from "react-icons/fa";
import { Link } from "react-router-dom";
import { storeImageToFireBase } from "../../../utils/storeImageToFirebase.";
import Load from "../../load/index";

import callApi from "../../../utils/api/callApi";

function Step({ steps, setSteps }) {


    const [isLoading, setIsLoading] = useState(false);
    const [selectedFile, setSelectedFile] = useState();

    const [stepName, setStepName] = useState("");
    const [ingredient, setIngredient] = useState({ id: 1, quantity: 0 });
    const [ingredients, setIngredients] = useState([]);
    const [description, setDescription] = useState("");
    const [image, setImage] = useState("");
    const [video, setVideo] = useState("");

    const [dataIng, setDataIng] = useState([]);


    useEffect(() => {
        callApi('ingredient').then(res => setDataIng(res.data)).catch(err => console.log(err));
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
                    setImage(imageUrl);
                    setIsLoading(false);
                    return imageUrl;
                } else {
                    console.log(message);
                }
                setIsLoading(false);
            };
            uploadImage();
        },
        [selectedFile]
    );

    const onSelectFile = (e) => {
        if (!e.target.files || e.target.files.length === 0) {
            setSelectedFile(undefined);
            return;
        }
        setSelectedFile(e.target.files[0]);
    };

    const handleDelIngredient = (index) => {
        setIngredients(ingredients.filter((item, i) => i !== index));
    }

    const handleAddIngredient = () => {
        if (!!ingredient.quantity) {
            setIngredients([...ingredients, ingredient]);
            setIngredient({ id: 1, quantity: 0 });
        }
    }

    const handleAddStep = () => {

        
        if (stepName && description) {
            const data = {
                step: stepName,
                description: description,
                image: image,
                video: video,
                ingredients: ingredients
            }

        console.log("data");

            setSteps((pre) => [...pre, data]);

            setStepName('');
            setIngredients([]);
            setDescription('');
            setImage('');
            setVideo('');
        }
    }

    const handleDelStep = (index) => {
        setSteps(pre => pre.filter((item, i) => i !== index));
    }

    return (
        <>
            <div className="w-full mt-10">
                {steps.map((item, index) => {
                    return (
                        <div key={index} className="mb-4">
                            <div className="flex gap-2 border  border-pink-600 rounded-md w-full shadow-sm hover:shadow-lg transition overflow-hidden">
                                <div className="rounded-md w-[30%] object-cover">
                                    {item.image &&
                                        <div className="inline-block w-full h-full">
                                            <img src={item.image} alt="" className="w-full h-full" />
                                        </div>
                                    }
                                </div>
                                <div className="p-4 flex-1 ">
                                    <div className="flex items-center justify-between w-full">
                                        <p className="text-2xl font-bold mb-4 text-pink-600 flex-1">{item.step ? item.step : ""}</p>
                                        <div onClick={() => handleDelStep(index)} className="w-9 cursor-pointer border-2 ml-4 h-9 py-1 px-2  hover:border-pink-300 bg-pink-200 rounded-full">
                                            <svg xmlns="http://www.w3.org/2000/svg" width='16px' hieght='16px' fill="#db2777" viewBox="0 0 320 512">
                                                <path d="M310.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L160 210.7 54.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L114.7 256 9.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L160 301.3 265.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L205.3 256 310.6 150.6z" />
                                            </svg>
                                        </div>
                                    </div>
                                    <div className="">
                                        <p className="mt-2 line-clmap-4">{item.description}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    );
                })}
            </div>
            <div noValidate>
                <div className="grid lg:grid-cols-3 mt-12 gap-8 rounded-md lg:shadow-lg shadow lg:p-6 p-4 bg-white dark:bg-gray-900 ">
                    <div>
                        <div className="px-4 py-3 -mx-5 -mt-4 mb-5 border-b">
                            <h4>Photo</h4>
                        </div>
                        <div className="flex-center text-center dark:text-gray-300  bg-white dark:bg-gray-900">
                            <div className="flex flex-col choose-upload text-center">
                                {image ? (
                                    <div
                                        className="bg-gray-400 border-2 border-dashed flex flex-col h-56 items-center justify-center relative w-full rounded-lg "
                                        style={{
                                            backgroundImage: `url(${image})`,
                                        }}
                                    >
                                        <Link
                                            to="#"
                                            className="hover:text-red-400 text-red-500"
                                            style={{
                                                position: "absolute",
                                                right: "10px",
                                                top: "10px",
                                            }}
                                            onClick={() => setImage('')}
                                        >
                                            <FaTrashAlt />
                                        </Link>
                                    </div>
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
                    </div>
                    <div className="bg-white dark:bg-gray-900 col-span-2 ">
                        <div className="grid grid-cols-2 gap-3 ">
                            <>
                                <div className="col-span-2">
                                    <label htmlFor> Tên Bước</label>
                                    <input type="text" name="name" placeholder="name post.." className="shadow-none bg-gray-100" onChange={e => setStepName(e.target.value)} value={stepName} />
                                </div>
                                <div className="col-span-2">
                                    <label htmlFor> Nguyên liệu</label>
                                    <div className="flex gap-2  flex-wrap">
                                        {ingredients?.map((item, index) => {
                                            return (
                                                <div onClick={() => handleDelIngredient(index)} key={index} className="inline-block ">
                                                    <div className="flex gap-2 items-center  px-5 py-1 border rounded-full ">
                                                        <span>{dataIng.find(Ing => Ing.id === item.id).ingredients}</span> -<span>{item.quantity + " " + dataIng.find(Ing => Ing.id === item.id).unit}</span>
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
                                        <select value={ingredient.id} onChange={e => setIngredient({ ...ingredient, id: +e.target.value })} name="tool" className="w-full mt-4 px-4 bg-gray-100">
                                            {dataIng?.map((item, index) => {
                                                return (
                                                    <option key={index} value={item.id}>
                                                        {item.ingredients}
                                                    </option>
                                                );
                                            })}
                                        </select>
                                        <div className="flex gap-4">
                                            <input
                                                type="number"
                                                name="quantity"
                                                placeholder="Quantity..."
                                                className="shadow-none bg-gray-100 mt-4 p-4 flex-1 "
                                                value={ingredient.quantity}
                                                onChange={e => setIngredient({ ...ingredient, quantity: e.target.value })}
                                            /><span className="mt-7">{dataIng.find(Ing => Ing.id === ingredient.id)?.unit}</span>
                                            <button onClick={handleAddIngredient} className="button bg-blue-700 mt-4 text-center">
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
                                        value={description}
                                        onChange={e => setDescription(e.target.value)}
                                    />
                                </div>
                            </>
                        </div>
                    </div>
                </div>

                <div className="bg-gray-10 p-6 pt-10 flex justify-end space-x-3">
                    <button onClick={handleAddStep} className="button bg-blue-700">
                        Add Step
                    </button>
                </div>
            </div>
        </>

    );
}

export default Step;