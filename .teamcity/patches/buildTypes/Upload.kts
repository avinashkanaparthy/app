package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, remove the buildType with id = 'Upload'
from your code, and delete the patch script.
*/
deleteBuildType(RelativeId("Upload"))

