import React, { useEffect } from "react";
import SectionContainer from "../SectionContainer";
import SectionHeader from "../SectionHeader";
import styles from "./Popular.module.css";
import Aos from "aos";
import "aos/dist/aos.css";
import "aos/dist/aos.js";
import { Link } from "react-router-dom";

const Popular = () => {
  useEffect(() => {
    Aos.init({ duration: 2000 });
    Aos.refresh();
  }, []);
  return (
    <div data-aos={"fade-down"}>
      <SectionContainer>
        <SectionHeader>Peek into some saved bakery recipe</SectionHeader>
        <div className={styles["trip-container"]}>
          <div className={styles["trip-items"]}>
            <div className="grid lg:grid-cols-4 grid-cols-2 gap-2 hover:text-yellow-700 uk-link-reset">
              <div>
                <div className="bg-red-400 max-w-full lg:h-64 h-4 rounded-md relative overflow-hidden uk-transition-toggle shadow-sm">
                  <a href="#story-modal" uk-toggle>
                    <img
                      src={`https://firebasestorage.googleapis.com/v0/b/bakery-9a92d.appspot.com/o/images%2F1.jpg?alt=media&token=c046cbee-631f-40d6-9fd4-91015d57e17d`}
                      alt=""
                      className="w-full h-full absolute object-cover inset-0"
                    />
                  </a>
                  <div className="absolut absolute bottom-0 flex items-center justify-between px-4 py-3 space-x-2 text-white w-full custom-overly1">
                    <Link to="#">Johnson </Link>
                    <div className="flex space-x-3">
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="heart"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="heart"
                        />
                        150
                      </Link>
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="chatbubble-ellipses"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="chatbubble ellipses"
                        />
                        30
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
              <div className="lg:col-span-2 lg:row-span-2">
                <div className="bg-pink-400 h-full max-w-full overflow-hidden relative rounded-md uk-transition-toggle shadow-sm">
                  <a href="#story-modal" uk-toggle>
                    <img
                      src={`https://firebasestorage.googleapis.com/v0/b/bakery-9a92d.appspot.com/o/images%2F3.jpg?alt=media&token=6032d58a-d06d-4538-855a-acc4dfa46e69`}
                      alt=""
                      className="w-full h-full absolute object-cover inset-0 transform scale-125"
                    />
                  </a>
                  <div className="absolut absolute bottom-0 p-6 space-y-2 text-white w-full custom-overly1 uk-light lg:block hidden">
                    <div className="flex flex-1 items-center space-x-2">
                      <Link to="#" className="flex items-center">
                        <div className="bg-gradient-to-tr from-yellow-600 to-pink-600 p-1 rounded-full transform -rotate-2 hover:rotate-3 transition hover:scale-105 m-0.5 mr-2">
                          <img
                            src={`https://firebasestorage.googleapis.com/v0/b/bakery-9a92d.appspot.com/o/images%2F3.jpg?alt=media&token=6032d58a-d06d-4538-855a-acc4dfa46e69`}
                            alt=""
                            className="bg-gray-200 border border-white rounded-full w-8"
                          />
                        </div>
                        Monroe Parker
                      </Link>
                      <div className="flex space-x-3">
                        <Link to="#" className="flex items-center">
                          <ion-icon
                            name="heart"
                            className="mr-1 md hydrated"
                            role="img"
                            aria-label="heart"
                          />
                          150
                        </Link>
                        <Link to="#" className="flex items-center">
                          <ion-icon
                            name="chatbubble-ellipses"
                            className="mr-1 md hydrated"
                            role="img"
                            aria-label="chatbubble ellipses"
                          ></ion-icon>
                          30
                        </Link>
                      </div>
                    </div>
                    <h1 className="font-bold text-3xl">
                      Our Bakery's Coconut Macaroons
                    </h1>
                    <p>
                      Standing tall with their lightly toasted edges, these
                      chewy and moist cookies are a beloved item in the King
                      Arthur Bakery case thanks to their intense coconut flavor
                    </p>
                  </div>
                </div>
              </div>
              <div>
                <div className="bg-green-400 max-w-full lg:h-64 h-40 rounded-md relative overflow-hidden uk-transition-toggle shadow-sm">
                  <a href="#story-modal" uk-toggle>
                    <img
                      src={`https://firebasestorage.googleapis.com/v0/b/bakery-9a92d.appspot.com/o/images%2F2.jpg?alt=media&token=9e9a960f-e038-49f0-aed8-7cd27dea458a`}
                      alt=""
                      className="w-full h-full absolute object-cover inset-0"
                    />
                  </a>
                  <div className="absolut absolute bottom-0 flex items-center justify-between px-4 py-3 space-x-2 text-white w-full custom-overly1">
                    <Link to="#">James</Link>
                    <div className="flex space-x-3">
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="heart"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="heart"
                        />
                        150
                      </Link>
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="chatbubble-ellipses"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="chatbubble ellipses"
                        />
                        30
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
              <div>
                <div className="bg-yellow-400 max-w-full lg:h-64 h-40 rounded-md relative overflow-hidden shadow-sm">
                  <a href="#story-modal" uk-toggle>
                    <img
                      src={`https://firebasestorage.googleapis.com/v0/b/bakery-9a92d.appspot.com/o/images%2F4.jpg?alt=media&token=87695b9b-4b21-40dc-92bf-e26081841427`}
                      alt=""
                      className="w-full h-full absolute object-cover inset-0 object-cover object-left"
                    />
                  </a>
                  <div className="absolut absolute bottom-0 flex items-center justify-between px-4 py-3 space-x-2 text-white w-full custom-overly1">
                    <Link to="#">Martin</Link>
                    <div className="flex space-x-3">
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="heart"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="heart"
                        />
                        150
                      </Link>
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="chatbubble-ellipses"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="chatbubble ellipses"
                        />
                        30
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
              <div>
                <div className="bg-purple-400 max-w-full lg:h-64 h-40 rounded-md relative overflow-hidden shadow-sm">
                  <a href="#story-modal" uk-toggle>
                    <img
                      src={`https://firebasestorage.googleapis.com/v0/b/bakery-9a92d.appspot.com/o/images%2F5.jpg?alt=media&token=f9f93cae-a1a4-4381-ac52-7fa78c83beab`}
                      alt=""
                      className="w-full h-full absolute object-cover inset-0"
                    />
                  </a>
                  <div className="absolut absolute bottom-0 flex items-center justify-between px-4 py-3 space-x-2 text-white w-full custom-overly1">
                    <Link to="#">Johnson </Link>
                    <div className="flex space-x-3">
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="heart"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="heart"
                        />
                        150
                      </Link>
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="chatbubble-ellipses"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="chatbubble ellipses"
                        />
                        30
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
              <div>
                <div className="bg-blue-400 max-w-full lg:h-64 h-40 rounded-md relative overflow-hidden shadow-sm">
                  <a href="#story-modal" uk-toggle>
                    <img
                      src={`https://firebasestorage.googleapis.com/v0/b/bakery-9a92d.appspot.com/o/images%2F6.jpg?alt=media&token=0fafc071-7da0-4559-9dd1-268375c30011`}
                      alt=""
                      className="w-full h-full absolute object-cover inset-0"
                    />
                  </a>
                  <div className="absolut absolute bottom-0 flex items-center justify-between px-4 py-3 space-x-2 text-white w-full custom-overly1">
                    <Link to="#">Jesse</Link>
                    <div className="flex space-x-3">
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="heart"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="heart"
                        />
                        150
                      </Link>
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="chatbubble-ellipses"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="chatbubble ellipses"
                        />
                        30
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
              <div>
                <div className="bg-green-400 max-w-full lg:h-64 h-40 rounded-md relative overflow-hidden shadow-sm">
                  <a href="#story-modal" uk-toggle>
                    <img
                      src={`https://firebasestorage.googleapis.com/v0/b/bakery-9a92d.appspot.com/o/images%2F7.jpg?alt=media&token=28dbc8b9-2513-4511-b5ab-673a8bb22dd1`}
                      alt=""
                      className="w-full h-full absolute object-cover inset-0"
                    />
                  </a>
                  <div className="absolut absolute bottom-0 flex items-center justify-between px-4 py-3 space-x-2 text-white w-full custom-overly1">
                    <Link to="#">Johnson </Link>
                    <div className="flex space-x-3">
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="heart"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="heart"
                        />
                        150
                      </Link>
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="chatbubble-ellipses"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="chatbubble ellipses"
                        />
                        30
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
              <div>
                <div className="bg-red-400 max-w-full lg:h-64 h-40 rounded-md relative overflow-hidden shadow-sm">
                  <a href="#story-modal" uk-toggle>
                    <img
                      src={`https://firebasestorage.googleapis.com/v0/b/bakery-9a92d.appspot.com/o/images%2F8.jpg?alt=media&token=be9ea350-316a-48bf-ae1c-519b0c0b0e89`}
                      alt=""
                      className="w-full h-full absolute object-cover inset-0"
                    />
                  </a>
                  <div className="absolut absolute bottom-0 flex items-center justify-between px-4 py-3 space-x-2 text-white w-full custom-overly1">
                    <Link to="#">James</Link>
                    <div className="flex space-x-3">
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="heart"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="heart"
                        />
                        150
                      </Link>
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="chatbubble-ellipses"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="chatbubble ellipses"
                        />
                        30
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
              <div>
                <div className="bg-yellow-400 max-w-full lg:h-64 h-40 rounded-md relative overflow-hidden shadow-sm">
                  <a href="#story-modal" uk-toggle>
                    <img
                      src={`https://firebasestorage.googleapis.com/v0/b/bakery-9a92d.appspot.com/o/images%2F9.jpg?alt=media&token=b63e72f1-85d6-4df9-888e-7f65556b78b9`}
                      alt=""
                      className="w-full h-full absolute object-cover inset-0"
                    />
                  </a>
                  <div className="absolut absolute bottom-0 flex items-center justify-between px-4 py-3 space-x-2 text-white w-full custom-overly1">
                    <Link to="#">Monroe</Link>
                    <div className="flex space-x-3">
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="heart"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="heart"
                        />
                        150
                      </Link>
                      <Link to="#" className="flex items-center">
                        <ion-icon
                          name="chatbubble-ellipses"
                          className="mr-1 md hydrated"
                          role="img"
                          aria-label="chatbubble ellipses"
                        />
                        30
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </SectionContainer>
    </div>
  );
};

export default Popular;
