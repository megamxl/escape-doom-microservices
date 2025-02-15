import {defineConfig} from "@kubb/core";
import {getConfig} from "./base.kubb.cofig";

export default defineConfig(getConfig("session","v1"))