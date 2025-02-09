import {defineConfig} from "@kubb/core";
import loadConfigWithPath from "./base-config";

export const config = loadConfigWithPath("../services/data-service/src/main/resources/api.yml")

export default defineConfig(config)