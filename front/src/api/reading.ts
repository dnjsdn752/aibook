import axios from "axios";

/**
 * 특정 사용자(userId)의 대여한 책 목록을 조회하는 API
 */
export const getUserReadings = async (userId: number) => {
  try {
    const response = await axios.get(
      `https://expert-parakeet-g4rx999gq7w5h97r-8088.app.github.dev/readings/user/${userId}`
    );
    return response.data;
  } catch (error) {
    console.error("📛 getUserReadings error:", error);
    return [];
  }
};
