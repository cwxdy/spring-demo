package com.example.demo.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
/**
 * @Author: ChangYu
 * @Version 1.0
 */
/**
 * 启动后加载程序
 */
@Component
public class StartupRunner implements ApplicationRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("\n" +
                "//                            _ooOoo_\n" +
                "//                           o8888888o\n" +
                "//                           88\" . \"88\n" +
                "//                           (| -_- |)\n" +
                "//                           O\\  =  /O\n" +
                "//                        ____/`---'\\____\n" +
                "//                      .'  \\\\|     |//  `.\n" +
                "//                     /  \\\\|||  :  |||//  \\\n" +
                "//                    /  _||||| -:- |||||-  \\\n" +
                "//                    |   | \\\\\\  -  /// |   |\n" +
                "//                    | \\_|  ''\\---/''  |   |\n" +
                "//                    \\  .-\\__  `-`  ___/-. /\n" +
                "//                  ___`. .'  /--.--\\  `. . __\n" +
                "//               .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n" +
                "//              | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
                "//              \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n" +
                "//         ======`-.____`-.___\\_____/___.-`____.-'======\n" +
                "//                            `=---='\n" +
                "//        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
                "//                      佛祖出现       永无BUG" +
                "");
    }
}
