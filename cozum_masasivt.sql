SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for problems
-- ----------------------------
DROP TABLE IF EXISTS `problems`;
CREATE TABLE `problems`  (
  `ProbID` int(11) NOT NULL AUTO_INCREMENT,
  `problem` text CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci,
  `date` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ProbID`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 71 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_turkish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problems
-- ----------------------------
INSERT INTO `problems` VALUES (10, 'Ornek problem.', '2017-08-25 15:50:07');

-- ----------------------------
-- Table structure for remarks
-- ----------------------------
DROP TABLE IF EXISTS `remarks`;
CREATE TABLE `remarks`  (
  `RemarkID` int(11) NOT NULL AUTO_INCREMENT,
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci,
  `ProbID` int(11) NOT NULL,
  PRIMARY KEY (`RemarkID`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_turkish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of remarks
-- ----------------------------
INSERT INTO `remarks` VALUES (5, 'Ornek not.', 10);

-- ----------------------------
-- Table structure for solutions
-- ----------------------------
DROP TABLE IF EXISTS `solutions`;
CREATE TABLE `solutions`  (
  `SolutionID` int(11) NOT NULL AUTO_INCREMENT,
  `solution` text CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci,
  `ProbID` int(11) NOT NULL,
  PRIMARY KEY (`SolutionID`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_turkish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of solutions
-- ----------------------------
INSERT INTO `solutions` VALUES (30, 'Ornek cozum.', 10);

-- ----------------------------
-- Table structure for tagproblems
-- ----------------------------
DROP TABLE IF EXISTS `tagproblems`;
CREATE TABLE `tagproblems`  (
  `TagProbID` int(11) NOT NULL AUTO_INCREMENT,
  `TagID` int(255) NOT NULL,
  `ProbID` int(255) NOT NULL,
  PRIMARY KEY (`TagProbID`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_turkish_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of tagproblems
-- ----------------------------

INSERT INTO `tagproblems` VALUES (22, 12, 10);

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags`  (
  `TagID` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`TagID`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_turkish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tags
-- ----------------------------
INSERT INTO `tags` VALUES (12, 'Ornek etiket.');

SET FOREIGN_KEY_CHECKS = 1;
