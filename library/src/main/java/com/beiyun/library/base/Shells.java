package com.beiyun.library.base;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by beiyun on 2017/11/7.
 * copy from url: https://github.com/Blankj/AndroidUtilCode
 */
class Shells {

    private static final String LINE_SEP = System.getProperty("line.separator");


    /**
     * 是否是在root下执行命令
     *
     * @param command 命令
     * @param isRoot  是否需要root权限执行
     * @return CommandResult
     */
    public static CommandResult execCmd(final String command, final boolean isRoot) {
        return execCmd(new String[]{command}, isRoot, true);
    }

    /**
     * 是否是在root下执行命令
     *
     * @param commands 多条命令链表
     * @param isRoot   是否需要root权限执行
     * @return CommandResult
     */
    public static CommandResult execCmd(final List<String> commands, final boolean isRoot) {
        return execCmd(commands == null ? null : commands.toArray(new String[commands.size()]), isRoot, true);
    }

    /**
     * 是否是在root下执行命令
     *
     * @param commands 多条命令数组
     * @param isRoot   是否需要root权限执行
     * @return CommandResult
     */
    public static CommandResult execCmd(final String[] commands, final boolean isRoot) {
        return execCmd(commands, isRoot, true);
    }

    /**
     * 是否是在root下执行命令
     *
     * @param command         命令
     * @param isRoot          是否需要root权限执行
     * @param isNeedResultMsg 是否需要结果消息
     * @return CommandResult
     */
    public static CommandResult execCmd(final String command, final boolean isRoot, final boolean isNeedResultMsg) {
        return execCmd(new String[]{command}, isRoot, isNeedResultMsg);
    }

    /**
     * 是否是在root下执行命令
     *
     * @param commands        命令链表
     * @param isRoot          是否需要root权限执行
     * @param isNeedResultMsg 是否需要结果消息
     * @return CommandResult
     */
    public static CommandResult execCmd(final List<String> commands, final boolean isRoot, final boolean isNeedResultMsg) {
        return execCmd(commands == null ? null : commands.toArray(new String[]{}), isRoot, isNeedResultMsg);
    }

    /**
     * 是否是在root下执行命令
     *
     * @param commands        命令数组
     * @param isRoot          是否需要root权限执行
     * @param isNeedResultMsg 是否需要结果消息
     * @return CommandResult
     */
    public static CommandResult execCmd(final String[] commands, final boolean isRoot, final boolean isNeedResultMsg) {
        int result = -1;
        if (commands == null || commands.length == 0) {
            return new CommandResult(result, null, null);
        }
        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec(isRoot ? "su" : "sh");
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) continue;
                os.write(command.getBytes());
                os.writeBytes(LINE_SEP);
                os.flush();
            }
            os.writeBytes("exit" + LINE_SEP);
            os.flush();
            result = process.waitFor();
            if (isNeedResultMsg) {
                successMsg = new StringBuilder();
                errorMsg = new StringBuilder();
                successResult = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
                errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
                String line;
                if ((line = successResult.readLine()) != null) {
                    successMsg.append(line);
                    while ((line = successResult.readLine()) != null) {
                        successMsg.append(LINE_SEP).append(line);
                    }
                }
                if ((line = errorResult.readLine()) != null) {
                    errorMsg.append(line);
                    while ((line = errorResult.readLine()) != null) {
                        errorMsg.append(LINE_SEP).append(line);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
                try {
                    if(os != null){
                     os.close();
                    }

                    if(successResult != null){
                        successResult.close();
                    }

                    if(errorResult != null){
                        errorResult.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (process != null) {
                process.destroy();
            }
        }
        return new CommandResult(
                result,
                successMsg == null ? null : successMsg.toString(),
                errorMsg == null ? null : errorMsg.toString()
        );
    }

    /**
     * 返回的命令结果
     */
    public static class CommandResult {
        /**
         * 结果码
         **/
        public int    result;
        /**
         * 成功信息
         **/
        public String successMsg;
        /**
         * 错误信息
         **/
        public String errorMsg;

        public CommandResult(final int result, final String successMsg, final String errorMsg) {
            this.result = result;
            this.successMsg = successMsg;
            this.errorMsg = errorMsg;
        }
    }
}
