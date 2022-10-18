// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getStorage } from "firebase/storage";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyC6aUjnHBuSqcsIF-hfGJTHoXTV8ePqgTo",
  authDomain: "bakery-9a92d.firebaseapp.com",
  projectId: "bakery-9a92d",
  storageBucket: "bakery-9a92d.appspot.com",
  messagingSenderId: "756762069461",
  appId: "1:756762069461:web:35c7491a353e0ca6c6955c",
  measurementId: "G-5LNST4WTHN",
};
// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const storage = getStorage(app);
