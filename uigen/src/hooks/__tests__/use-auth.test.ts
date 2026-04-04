import { describe, test, expect, vi, beforeEach } from "vitest";
import { renderHook, act } from "@testing-library/react";
import { useAuth } from "@/hooks/use-auth";
import * as anonTracker from "@/lib/anon-work-tracker";

const mockPush = vi.fn();

vi.mock("next/navigation", () => ({
  useRouter: () => ({ push: mockPush }),
}));

vi.mock("@/actions", () => ({
  signIn: vi.fn(),
  signUp: vi.fn(),
}));

vi.mock("@/lib/anon-work-tracker", () => ({
  getAnonWorkData: vi.fn(),
  clearAnonWork: vi.fn(),
}));

vi.mock("@/actions/get-projects", () => ({
  getProjects: vi.fn(),
}));

vi.mock("@/actions/create-project", () => ({
  createProject: vi.fn(),
}));

import { signIn as signInAction, signUp as signUpAction } from "@/actions";
import { getProjects } from "@/actions/get-projects";
import { createProject } from "@/actions/create-project";

const mockSignInAction = vi.mocked(signInAction);
const mockSignUpAction = vi.mocked(signUpAction);
const mockGetAnonWorkData = vi.mocked(anonTracker.getAnonWorkData);
const mockClearAnonWork = vi.mocked(anonTracker.clearAnonWork);
const mockGetProjects = vi.mocked(getProjects);
const mockCreateProject = vi.mocked(createProject);

beforeEach(() => {
  vi.clearAllMocks();
});

describe("useAuth", () => {
  test("returns signIn, signUp, and isLoading", () => {
    const { result } = renderHook(() => useAuth());

    expect(result.current.signIn).toBeTypeOf("function");
    expect(result.current.signUp).toBeTypeOf("function");
    expect(result.current.isLoading).toBe(false);
  });

  describe("signIn", () => {
    test("calls signInAction and returns the result", async () => {
      mockSignInAction.mockResolvedValue({ success: true });
      mockGetAnonWorkData.mockReturnValue(null);
      mockGetProjects.mockResolvedValue([]);
      mockCreateProject.mockResolvedValue({ id: "new-1" } as any);

      const { result } = renderHook(() => useAuth());

      let returnValue: any;
      await act(async () => {
        returnValue = await result.current.signIn("a@b.com", "password123");
      });

      expect(mockSignInAction).toHaveBeenCalledWith("a@b.com", "password123");
      expect(returnValue).toEqual({ success: true });
    });

    test("sets isLoading to true during sign in and false after", async () => {
      let resolveSignIn: (v: any) => void;
      mockSignInAction.mockReturnValue(
        new Promise((r) => {
          resolveSignIn = r;
        })
      );

      const { result } = renderHook(() => useAuth());
      expect(result.current.isLoading).toBe(false);

      let signInPromise: Promise<any>;
      act(() => {
        signInPromise = result.current.signIn("a@b.com", "password123");
      });

      expect(result.current.isLoading).toBe(true);

      mockGetAnonWorkData.mockReturnValue(null);
      mockGetProjects.mockResolvedValue([]);
      mockCreateProject.mockResolvedValue({ id: "p1" } as any);

      await act(async () => {
        resolveSignIn!({ success: true });
        await signInPromise!;
      });

      expect(result.current.isLoading).toBe(false);
    });

    test("resets isLoading when signInAction fails", async () => {
      mockSignInAction.mockResolvedValue({ success: false, error: "Bad creds" });

      const { result } = renderHook(() => useAuth());

      await act(async () => {
        await result.current.signIn("a@b.com", "wrong");
      });

      expect(result.current.isLoading).toBe(false);
    });

    test("resets isLoading when signInAction throws", async () => {
      mockSignInAction.mockRejectedValue(new Error("Network error"));

      const { result } = renderHook(() => useAuth());

      await act(async () => {
        await expect(
          result.current.signIn("a@b.com", "password123")
        ).rejects.toThrow("Network error");
      });

      expect(result.current.isLoading).toBe(false);
    });

    test("does not navigate when sign in fails", async () => {
      mockSignInAction.mockResolvedValue({
        success: false,
        error: "Invalid",
      });

      const { result } = renderHook(() => useAuth());

      await act(async () => {
        await result.current.signIn("a@b.com", "wrong");
      });

      expect(mockPush).not.toHaveBeenCalled();
    });
  });

  describe("signUp", () => {
    test("calls signUpAction and returns the result", async () => {
      mockSignUpAction.mockResolvedValue({ success: true });
      mockGetAnonWorkData.mockReturnValue(null);
      mockGetProjects.mockResolvedValue([]);
      mockCreateProject.mockResolvedValue({ id: "new-1" } as any);

      const { result } = renderHook(() => useAuth());

      let returnValue: any;
      await act(async () => {
        returnValue = await result.current.signUp("a@b.com", "password123");
      });

      expect(mockSignUpAction).toHaveBeenCalledWith("a@b.com", "password123");
      expect(returnValue).toEqual({ success: true });
    });

    test("sets isLoading during sign up and resets after", async () => {
      mockSignUpAction.mockResolvedValue({ success: false, error: "exists" });

      const { result } = renderHook(() => useAuth());

      await act(async () => {
        await result.current.signUp("a@b.com", "password123");
      });

      expect(result.current.isLoading).toBe(false);
    });

    test("resets isLoading when signUpAction throws", async () => {
      mockSignUpAction.mockRejectedValue(new Error("Server error"));

      const { result } = renderHook(() => useAuth());

      await act(async () => {
        await expect(
          result.current.signUp("a@b.com", "password123")
        ).rejects.toThrow("Server error");
      });

      expect(result.current.isLoading).toBe(false);
    });

    test("does not navigate when sign up fails", async () => {
      mockSignUpAction.mockResolvedValue({
        success: false,
        error: "Already exists",
      });

      const { result } = renderHook(() => useAuth());

      await act(async () => {
        await result.current.signUp("a@b.com", "password123");
      });

      expect(mockPush).not.toHaveBeenCalled();
    });
  });

  describe("post-sign-in navigation", () => {
    test("migrates anonymous work and navigates to its project", async () => {
      const anonData = {
        messages: [{ role: "user", content: "hello" }],
        fileSystemData: { "/": { type: "directory" } },
      };
      mockSignInAction.mockResolvedValue({ success: true });
      mockGetAnonWorkData.mockReturnValue(anonData);
      mockCreateProject.mockResolvedValue({ id: "migrated-proj" } as any);

      const { result } = renderHook(() => useAuth());

      await act(async () => {
        await result.current.signIn("a@b.com", "password123");
      });

      expect(mockCreateProject).toHaveBeenCalledWith(
        expect.objectContaining({
          messages: anonData.messages,
          data: anonData.fileSystemData,
        })
      );
      expect(mockClearAnonWork).toHaveBeenCalled();
      expect(mockPush).toHaveBeenCalledWith("/migrated-proj");
      expect(mockGetProjects).not.toHaveBeenCalled();
    });

    test("skips anonymous work with empty messages", async () => {
      mockSignInAction.mockResolvedValue({ success: true });
      mockGetAnonWorkData.mockReturnValue({ messages: [], fileSystemData: {} });
      mockGetProjects.mockResolvedValue([{ id: "existing" } as any]);

      const { result } = renderHook(() => useAuth());

      await act(async () => {
        await result.current.signIn("a@b.com", "password123");
      });

      expect(mockClearAnonWork).not.toHaveBeenCalled();
      expect(mockGetProjects).toHaveBeenCalled();
      expect(mockPush).toHaveBeenCalledWith("/existing");
    });

    test("navigates to most recent project when no anon work", async () => {
      mockSignInAction.mockResolvedValue({ success: true });
      mockGetAnonWorkData.mockReturnValue(null);
      mockGetProjects.mockResolvedValue([
        { id: "proj-1" } as any,
        { id: "proj-2" } as any,
      ]);

      const { result } = renderHook(() => useAuth());

      await act(async () => {
        await result.current.signIn("a@b.com", "password123");
      });

      expect(mockPush).toHaveBeenCalledWith("/proj-1");
    });

    test("creates a new project when user has none", async () => {
      mockSignInAction.mockResolvedValue({ success: true });
      mockGetAnonWorkData.mockReturnValue(null);
      mockGetProjects.mockResolvedValue([]);
      mockCreateProject.mockResolvedValue({ id: "fresh-proj" } as any);

      const { result } = renderHook(() => useAuth());

      await act(async () => {
        await result.current.signIn("a@b.com", "password123");
      });

      expect(mockCreateProject).toHaveBeenCalledWith(
        expect.objectContaining({
          messages: [],
          data: {},
        })
      );
      expect(mockPush).toHaveBeenCalledWith("/fresh-proj");
    });

    test("post-sign-in also works after signUp", async () => {
      mockSignUpAction.mockResolvedValue({ success: true });
      mockGetAnonWorkData.mockReturnValue(null);
      mockGetProjects.mockResolvedValue([]);
      mockCreateProject.mockResolvedValue({ id: "signup-proj" } as any);

      const { result } = renderHook(() => useAuth());

      await act(async () => {
        await result.current.signUp("new@user.com", "password123");
      });

      expect(mockCreateProject).toHaveBeenCalled();
      expect(mockPush).toHaveBeenCalledWith("/signup-proj");
    });
  });
});
