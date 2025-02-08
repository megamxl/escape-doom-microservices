import type { NextConfig } from "next";

const nextConfig: NextConfig = {
    output: 'standalone',
    basePath: '/frontend',
    eslint: {
        ignoreDuringBuilds: true,
    },
    typescript: {
        ignoreBuildErrors: true
    },
  /* config options here */
};

export default nextConfig;
